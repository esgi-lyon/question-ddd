import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ITagStatsViewedEvent } from '../tag-stats-viewed-event.model';
import { TagStatsViewedEventService } from '../service/tag-stats-viewed-event.service';

import { TagStatsViewedEventRoutingResolveService } from './tag-stats-viewed-event-routing-resolve.service';

describe('TagStatsViewedEvent routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: TagStatsViewedEventRoutingResolveService;
  let service: TagStatsViewedEventService;
  let resultTagStatsViewedEvent: ITagStatsViewedEvent | null | undefined;

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
    routingResolveService = TestBed.inject(TagStatsViewedEventRoutingResolveService);
    service = TestBed.inject(TagStatsViewedEventService);
    resultTagStatsViewedEvent = undefined;
  });

  describe('resolve', () => {
    it('should return ITagStatsViewedEvent returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultTagStatsViewedEvent = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultTagStatsViewedEvent).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultTagStatsViewedEvent = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultTagStatsViewedEvent).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<ITagStatsViewedEvent>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultTagStatsViewedEvent = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultTagStatsViewedEvent).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
