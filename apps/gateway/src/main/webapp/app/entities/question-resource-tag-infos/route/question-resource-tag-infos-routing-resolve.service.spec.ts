import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IQuestionResourceTagInfos } from '../question-resource-tag-infos.model';
import { QuestionResourceTagInfosService } from '../service/question-resource-tag-infos.service';

import { QuestionResourceTagInfosRoutingResolveService } from './question-resource-tag-infos-routing-resolve.service';

describe('QuestionResourceTagInfos routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: QuestionResourceTagInfosRoutingResolveService;
  let service: QuestionResourceTagInfosService;
  let resultQuestionResourceTagInfos: IQuestionResourceTagInfos | null | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    routingResolveService = TestBed.inject(QuestionResourceTagInfosRoutingResolveService);
    service = TestBed.inject(QuestionResourceTagInfosService);
    resultQuestionResourceTagInfos = undefined;
  });

  describe('resolve', () => {
    it('should return IQuestionResourceTagInfos returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultQuestionResourceTagInfos = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultQuestionResourceTagInfos).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultQuestionResourceTagInfos = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultQuestionResourceTagInfos).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IQuestionResourceTagInfos>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultQuestionResourceTagInfos = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultQuestionResourceTagInfos).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
