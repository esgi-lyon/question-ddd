import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IQuestionSentTagId } from '../question-sent-tag-id.model';
import { QuestionSentTagIdService } from '../service/question-sent-tag-id.service';

import { QuestionSentTagIdRoutingResolveService } from './question-sent-tag-id-routing-resolve.service';

describe('QuestionSentTagId routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: QuestionSentTagIdRoutingResolveService;
  let service: QuestionSentTagIdService;
  let resultQuestionSentTagId: IQuestionSentTagId | null | undefined;

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
    routingResolveService = TestBed.inject(QuestionSentTagIdRoutingResolveService);
    service = TestBed.inject(QuestionSentTagIdService);
    resultQuestionSentTagId = undefined;
  });

  describe('resolve', () => {
    it('should return IQuestionSentTagId returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultQuestionSentTagId = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultQuestionSentTagId).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultQuestionSentTagId = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultQuestionSentTagId).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IQuestionSentTagId>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultQuestionSentTagId = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultQuestionSentTagId).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
