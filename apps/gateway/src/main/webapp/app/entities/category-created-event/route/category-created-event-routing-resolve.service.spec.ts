import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ICategoryCreatedEvent } from '../category-created-event.model';
import { CategoryCreatedEventService } from '../service/category-created-event.service';

import { CategoryCreatedEventRoutingResolveService } from './category-created-event-routing-resolve.service';

describe('CategoryCreatedEvent routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: CategoryCreatedEventRoutingResolveService;
  let service: CategoryCreatedEventService;
  let resultCategoryCreatedEvent: ICategoryCreatedEvent | null | undefined;

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
    routingResolveService = TestBed.inject(CategoryCreatedEventRoutingResolveService);
    service = TestBed.inject(CategoryCreatedEventService);
    resultCategoryCreatedEvent = undefined;
  });

  describe('resolve', () => {
    it('should return ICategoryCreatedEvent returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCategoryCreatedEvent = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultCategoryCreatedEvent).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCategoryCreatedEvent = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultCategoryCreatedEvent).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<ICategoryCreatedEvent>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCategoryCreatedEvent = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultCategoryCreatedEvent).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
