import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IEvaluationCreatedEvent } from '../evaluation-created-event.model';
import { EvaluationCreatedEventService } from '../service/evaluation-created-event.service';

import { EvaluationCreatedEventRoutingResolveService } from './evaluation-created-event-routing-resolve.service';

describe('EvaluationCreatedEvent routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: EvaluationCreatedEventRoutingResolveService;
  let service: EvaluationCreatedEventService;
  let resultEvaluationCreatedEvent: IEvaluationCreatedEvent | null | undefined;

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
    routingResolveService = TestBed.inject(EvaluationCreatedEventRoutingResolveService);
    service = TestBed.inject(EvaluationCreatedEventService);
    resultEvaluationCreatedEvent = undefined;
  });

  describe('resolve', () => {
    it('should return IEvaluationCreatedEvent returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultEvaluationCreatedEvent = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultEvaluationCreatedEvent).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultEvaluationCreatedEvent = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultEvaluationCreatedEvent).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IEvaluationCreatedEvent>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultEvaluationCreatedEvent = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultEvaluationCreatedEvent).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
