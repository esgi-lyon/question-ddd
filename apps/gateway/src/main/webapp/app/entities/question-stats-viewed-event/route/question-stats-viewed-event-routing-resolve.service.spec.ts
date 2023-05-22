import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IQuestionStatsViewedEvent } from '../question-stats-viewed-event.model';
import { QuestionStatsViewedEventService } from '../service/question-stats-viewed-event.service';

import { QuestionStatsViewedEventRoutingResolveService } from './question-stats-viewed-event-routing-resolve.service';

describe('QuestionStatsViewedEvent routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: QuestionStatsViewedEventRoutingResolveService;
  let service: QuestionStatsViewedEventService;
  let resultQuestionStatsViewedEvent: IQuestionStatsViewedEvent | null | undefined;

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
    routingResolveService = TestBed.inject(QuestionStatsViewedEventRoutingResolveService);
    service = TestBed.inject(QuestionStatsViewedEventService);
    resultQuestionStatsViewedEvent = undefined;
  });

  describe('resolve', () => {
    it('should return IQuestionStatsViewedEvent returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultQuestionStatsViewedEvent = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultQuestionStatsViewedEvent).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultQuestionStatsViewedEvent = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultQuestionStatsViewedEvent).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IQuestionStatsViewedEvent>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultQuestionStatsViewedEvent = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultQuestionStatsViewedEvent).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
