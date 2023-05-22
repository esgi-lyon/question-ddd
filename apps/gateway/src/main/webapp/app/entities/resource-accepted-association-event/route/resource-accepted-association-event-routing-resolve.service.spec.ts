import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IResourceAcceptedAssociationEvent } from '../resource-accepted-association-event.model';
import { ResourceAcceptedAssociationEventService } from '../service/resource-accepted-association-event.service';

import { ResourceAcceptedAssociationEventRoutingResolveService } from './resource-accepted-association-event-routing-resolve.service';

describe('ResourceAcceptedAssociationEvent routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: ResourceAcceptedAssociationEventRoutingResolveService;
  let service: ResourceAcceptedAssociationEventService;
  let resultResourceAcceptedAssociationEvent: IResourceAcceptedAssociationEvent | null | undefined;

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
    routingResolveService = TestBed.inject(ResourceAcceptedAssociationEventRoutingResolveService);
    service = TestBed.inject(ResourceAcceptedAssociationEventService);
    resultResourceAcceptedAssociationEvent = undefined;
  });

  describe('resolve', () => {
    it('should return IResourceAcceptedAssociationEvent returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultResourceAcceptedAssociationEvent = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultResourceAcceptedAssociationEvent).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultResourceAcceptedAssociationEvent = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultResourceAcceptedAssociationEvent).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IResourceAcceptedAssociationEvent>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultResourceAcceptedAssociationEvent = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultResourceAcceptedAssociationEvent).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
