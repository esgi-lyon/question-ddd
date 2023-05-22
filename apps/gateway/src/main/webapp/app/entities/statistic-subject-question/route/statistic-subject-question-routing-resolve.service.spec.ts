import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IStatisticSubjectQuestion } from '../statistic-subject-question.model';
import { StatisticSubjectQuestionService } from '../service/statistic-subject-question.service';

import { StatisticSubjectQuestionRoutingResolveService } from './statistic-subject-question-routing-resolve.service';

describe('StatisticSubjectQuestion routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: StatisticSubjectQuestionRoutingResolveService;
  let service: StatisticSubjectQuestionService;
  let resultStatisticSubjectQuestion: IStatisticSubjectQuestion | null | undefined;

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
    routingResolveService = TestBed.inject(StatisticSubjectQuestionRoutingResolveService);
    service = TestBed.inject(StatisticSubjectQuestionService);
    resultStatisticSubjectQuestion = undefined;
  });

  describe('resolve', () => {
    it('should return IStatisticSubjectQuestion returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultStatisticSubjectQuestion = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultStatisticSubjectQuestion).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultStatisticSubjectQuestion = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultStatisticSubjectQuestion).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IStatisticSubjectQuestion>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultStatisticSubjectQuestion = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultStatisticSubjectQuestion).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
