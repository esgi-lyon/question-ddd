import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IResourceWaitingForAssociationEvent } from '../resource-waiting-for-association-event.model';
import { ResourceWaitingForAssociationEventService } from '../service/resource-waiting-for-association-event.service';

import { ResourceWaitingForAssociationEventRoutingResolveService } from './resource-waiting-for-association-event-routing-resolve.service';

describe('ResourceWaitingForAssociationEvent routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: ResourceWaitingForAssociationEventRoutingResolveService;
  let service: ResourceWaitingForAssociationEventService;
  let resultResourceWaitingForAssociationEvent: IResourceWaitingForAssociationEvent | null | undefined;

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
    routingResolveService = TestBed.inject(ResourceWaitingForAssociationEventRoutingResolveService);
    service = TestBed.inject(ResourceWaitingForAssociationEventService);
    resultResourceWaitingForAssociationEvent = undefined;
  });

  describe('resolve', () => {
    it('should return IResourceWaitingForAssociationEvent returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultResourceWaitingForAssociationEvent = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultResourceWaitingForAssociationEvent).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultResourceWaitingForAssociationEvent = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultResourceWaitingForAssociationEvent).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IResourceWaitingForAssociationEvent>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultResourceWaitingForAssociationEvent = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultResourceWaitingForAssociationEvent).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
