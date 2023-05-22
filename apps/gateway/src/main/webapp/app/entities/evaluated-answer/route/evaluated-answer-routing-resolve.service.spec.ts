import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IEvaluatedAnswer } from '../evaluated-answer.model';
import { EvaluatedAnswerService } from '../service/evaluated-answer.service';

import { EvaluatedAnswerRoutingResolveService } from './evaluated-answer-routing-resolve.service';

describe('EvaluatedAnswer routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: EvaluatedAnswerRoutingResolveService;
  let service: EvaluatedAnswerService;
  let resultEvaluatedAnswer: IEvaluatedAnswer | null | undefined;

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
    routingResolveService = TestBed.inject(EvaluatedAnswerRoutingResolveService);
    service = TestBed.inject(EvaluatedAnswerService);
    resultEvaluatedAnswer = undefined;
  });

  describe('resolve', () => {
    it('should return IEvaluatedAnswer returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultEvaluatedAnswer = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultEvaluatedAnswer).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultEvaluatedAnswer = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultEvaluatedAnswer).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IEvaluatedAnswer>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultEvaluatedAnswer = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultEvaluatedAnswer).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
