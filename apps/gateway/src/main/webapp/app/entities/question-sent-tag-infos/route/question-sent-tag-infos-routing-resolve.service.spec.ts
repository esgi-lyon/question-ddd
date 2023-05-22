import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IQuestionSentTagInfos } from '../question-sent-tag-infos.model';
import { QuestionSentTagInfosService } from '../service/question-sent-tag-infos.service';

import { QuestionSentTagInfosRoutingResolveService } from './question-sent-tag-infos-routing-resolve.service';

describe('QuestionSentTagInfos routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: QuestionSentTagInfosRoutingResolveService;
  let service: QuestionSentTagInfosService;
  let resultQuestionSentTagInfos: IQuestionSentTagInfos | null | undefined;

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
    routingResolveService = TestBed.inject(QuestionSentTagInfosRoutingResolveService);
    service = TestBed.inject(QuestionSentTagInfosService);
    resultQuestionSentTagInfos = undefined;
  });

  describe('resolve', () => {
    it('should return IQuestionSentTagInfos returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultQuestionSentTagInfos = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultQuestionSentTagInfos).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultQuestionSentTagInfos = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultQuestionSentTagInfos).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IQuestionSentTagInfos>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultQuestionSentTagInfos = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultQuestionSentTagInfos).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
