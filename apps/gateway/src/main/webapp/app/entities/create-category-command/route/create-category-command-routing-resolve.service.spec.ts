import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ICreateCategoryCommand } from '../create-category-command.model';
import { CreateCategoryCommandService } from '../service/create-category-command.service';

import { CreateCategoryCommandRoutingResolveService } from './create-category-command-routing-resolve.service';

describe('CreateCategoryCommand routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: CreateCategoryCommandRoutingResolveService;
  let service: CreateCategoryCommandService;
  let resultCreateCategoryCommand: ICreateCategoryCommand | null | undefined;

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
    routingResolveService = TestBed.inject(CreateCategoryCommandRoutingResolveService);
    service = TestBed.inject(CreateCategoryCommandService);
    resultCreateCategoryCommand = undefined;
  });

  describe('resolve', () => {
    it('should return ICreateCategoryCommand returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCreateCategoryCommand = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultCreateCategoryCommand).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCreateCategoryCommand = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultCreateCategoryCommand).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<ICreateCategoryCommand>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCreateCategoryCommand = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultCreateCategoryCommand).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
