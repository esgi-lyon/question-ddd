import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IRejectResourceTagCommand } from '../reject-resource-tag-command.model';
import { RejectResourceTagCommandService } from '../service/reject-resource-tag-command.service';

import { RejectResourceTagCommandRoutingResolveService } from './reject-resource-tag-command-routing-resolve.service';

describe('RejectResourceTagCommand routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: RejectResourceTagCommandRoutingResolveService;
  let service: RejectResourceTagCommandService;
  let resultRejectResourceTagCommand: IRejectResourceTagCommand | null | undefined;

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
    routingResolveService = TestBed.inject(RejectResourceTagCommandRoutingResolveService);
    service = TestBed.inject(RejectResourceTagCommandService);
    resultRejectResourceTagCommand = undefined;
  });

  describe('resolve', () => {
    it('should return IRejectResourceTagCommand returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultRejectResourceTagCommand = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultRejectResourceTagCommand).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultRejectResourceTagCommand = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultRejectResourceTagCommand).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IRejectResourceTagCommand>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultRejectResourceTagCommand = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultRejectResourceTagCommand).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
