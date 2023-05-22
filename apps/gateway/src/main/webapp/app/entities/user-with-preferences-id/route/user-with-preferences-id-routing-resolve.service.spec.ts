import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IUserWithPreferencesId } from '../user-with-preferences-id.model';
import { UserWithPreferencesIdService } from '../service/user-with-preferences-id.service';

import { UserWithPreferencesIdRoutingResolveService } from './user-with-preferences-id-routing-resolve.service';

describe('UserWithPreferencesId routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: UserWithPreferencesIdRoutingResolveService;
  let service: UserWithPreferencesIdService;
  let resultUserWithPreferencesId: IUserWithPreferencesId | null | undefined;

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
    routingResolveService = TestBed.inject(UserWithPreferencesIdRoutingResolveService);
    service = TestBed.inject(UserWithPreferencesIdService);
    resultUserWithPreferencesId = undefined;
  });

  describe('resolve', () => {
    it('should return IUserWithPreferencesId returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultUserWithPreferencesId = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultUserWithPreferencesId).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultUserWithPreferencesId = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultUserWithPreferencesId).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IUserWithPreferencesId>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultUserWithPreferencesId = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultUserWithPreferencesId).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
