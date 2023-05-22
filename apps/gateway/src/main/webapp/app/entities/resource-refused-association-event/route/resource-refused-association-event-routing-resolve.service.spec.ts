import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IResourceRefusedAssociationEvent } from '../resource-refused-association-event.model';
import { ResourceRefusedAssociationEventService } from '../service/resource-refused-association-event.service';

import { ResourceRefusedAssociationEventRoutingResolveService } from './resource-refused-association-event-routing-resolve.service';

describe('ResourceRefusedAssociationEvent routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: ResourceRefusedAssociationEventRoutingResolveService;
  let service: ResourceRefusedAssociationEventService;
  let resultResourceRefusedAssociationEvent: IResourceRefusedAssociationEvent | null | undefined;

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
    routingResolveService = TestBed.inject(ResourceRefusedAssociationEventRoutingResolveService);
    service = TestBed.inject(ResourceRefusedAssociationEventService);
    resultResourceRefusedAssociationEvent = undefined;
  });

  describe('resolve', () => {
    it('should return IResourceRefusedAssociationEvent returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultResourceRefusedAssociationEvent = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultResourceRefusedAssociationEvent).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultResourceRefusedAssociationEvent = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultResourceRefusedAssociationEvent).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IResourceRefusedAssociationEvent>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultResourceRefusedAssociationEvent = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultResourceRefusedAssociationEvent).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
