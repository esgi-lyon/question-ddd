import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IAnswerSubmittedEvent } from '../answer-submitted-event.model';
import { AnswerSubmittedEventService } from '../service/answer-submitted-event.service';

import { AnswerSubmittedEventRoutingResolveService } from './answer-submitted-event-routing-resolve.service';

describe('AnswerSubmittedEvent routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: AnswerSubmittedEventRoutingResolveService;
  let service: AnswerSubmittedEventService;
  let resultAnswerSubmittedEvent: IAnswerSubmittedEvent | null | undefined;

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
    routingResolveService = TestBed.inject(AnswerSubmittedEventRoutingResolveService);
    service = TestBed.inject(AnswerSubmittedEventService);
    resultAnswerSubmittedEvent = undefined;
  });

  describe('resolve', () => {
    it('should return IAnswerSubmittedEvent returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultAnswerSubmittedEvent = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultAnswerSubmittedEvent).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultAnswerSubmittedEvent = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultAnswerSubmittedEvent).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IAnswerSubmittedEvent>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultAnswerSubmittedEvent = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultAnswerSubmittedEvent).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
