import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ICreatedQuestionEvent } from '../created-question-event.model';
import { CreatedQuestionEventService } from '../service/created-question-event.service';

import { CreatedQuestionEventRoutingResolveService } from './created-question-event-routing-resolve.service';

describe('CreatedQuestionEvent routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: CreatedQuestionEventRoutingResolveService;
  let service: CreatedQuestionEventService;
  let resultCreatedQuestionEvent: ICreatedQuestionEvent | null | undefined;

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
    routingResolveService = TestBed.inject(CreatedQuestionEventRoutingResolveService);
    service = TestBed.inject(CreatedQuestionEventService);
    resultCreatedQuestionEvent = undefined;
  });

  describe('resolve', () => {
    it('should return ICreatedQuestionEvent returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCreatedQuestionEvent = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultCreatedQuestionEvent).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCreatedQuestionEvent = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultCreatedQuestionEvent).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<ICreatedQuestionEvent>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCreatedQuestionEvent = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultCreatedQuestionEvent).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
