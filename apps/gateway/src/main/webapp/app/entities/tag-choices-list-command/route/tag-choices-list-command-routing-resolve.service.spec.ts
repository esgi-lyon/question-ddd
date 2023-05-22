import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ITagChoicesListCommand } from '../tag-choices-list-command.model';
import { TagChoicesListCommandService } from '../service/tag-choices-list-command.service';

import { TagChoicesListCommandRoutingResolveService } from './tag-choices-list-command-routing-resolve.service';

describe('TagChoicesListCommand routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: TagChoicesListCommandRoutingResolveService;
  let service: TagChoicesListCommandService;
  let resultTagChoicesListCommand: ITagChoicesListCommand | null | undefined;

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
    routingResolveService = TestBed.inject(TagChoicesListCommandRoutingResolveService);
    service = TestBed.inject(TagChoicesListCommandService);
    resultTagChoicesListCommand = undefined;
  });

  describe('resolve', () => {
    it('should return ITagChoicesListCommand returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultTagChoicesListCommand = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultTagChoicesListCommand).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultTagChoicesListCommand = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultTagChoicesListCommand).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<ITagChoicesListCommand>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultTagChoicesListCommand = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultTagChoicesListCommand).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
