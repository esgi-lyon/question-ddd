import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IQuestionSentQuestionResourceTagId } from '../question-sent-question-resource-tag-id.model';
import { QuestionSentQuestionResourceTagIdService } from '../service/question-sent-question-resource-tag-id.service';

import { QuestionSentQuestionResourceTagIdRoutingResolveService } from './question-sent-question-resource-tag-id-routing-resolve.service';

describe('QuestionSentQuestionResourceTagId routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: QuestionSentQuestionResourceTagIdRoutingResolveService;
  let service: QuestionSentQuestionResourceTagIdService;
  let resultQuestionSentQuestionResourceTagId: IQuestionSentQuestionResourceTagId | null | undefined;

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
    routingResolveService = TestBed.inject(QuestionSentQuestionResourceTagIdRoutingResolveService);
    service = TestBed.inject(QuestionSentQuestionResourceTagIdService);
    resultQuestionSentQuestionResourceTagId = undefined;
  });

  describe('resolve', () => {
    it('should return IQuestionSentQuestionResourceTagId returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultQuestionSentQuestionResourceTagId = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultQuestionSentQuestionResourceTagId).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultQuestionSentQuestionResourceTagId = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultQuestionSentQuestionResourceTagId).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IQuestionSentQuestionResourceTagId>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultQuestionSentQuestionResourceTagId = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultQuestionSentQuestionResourceTagId).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
