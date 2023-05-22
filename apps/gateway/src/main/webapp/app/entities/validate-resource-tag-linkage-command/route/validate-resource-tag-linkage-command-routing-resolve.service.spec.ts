import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IValidateResourceTagLinkageCommand } from '../validate-resource-tag-linkage-command.model';
import { ValidateResourceTagLinkageCommandService } from '../service/validate-resource-tag-linkage-command.service';

import { ValidateResourceTagLinkageCommandRoutingResolveService } from './validate-resource-tag-linkage-command-routing-resolve.service';

describe('ValidateResourceTagLinkageCommand routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: ValidateResourceTagLinkageCommandRoutingResolveService;
  let service: ValidateResourceTagLinkageCommandService;
  let resultValidateResourceTagLinkageCommand: IValidateResourceTagLinkageCommand | null | undefined;

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
    routingResolveService = TestBed.inject(ValidateResourceTagLinkageCommandRoutingResolveService);
    service = TestBed.inject(ValidateResourceTagLinkageCommandService);
    resultValidateResourceTagLinkageCommand = undefined;
  });

  describe('resolve', () => {
    it('should return IValidateResourceTagLinkageCommand returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultValidateResourceTagLinkageCommand = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultValidateResourceTagLinkageCommand).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultValidateResourceTagLinkageCommand = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultValidateResourceTagLinkageCommand).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IValidateResourceTagLinkageCommand>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultValidateResourceTagLinkageCommand = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultValidateResourceTagLinkageCommand).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
