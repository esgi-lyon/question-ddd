import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ICreateEvaluationCommand } from '../create-evaluation-command.model';
import { CreateEvaluationCommandService } from '../service/create-evaluation-command.service';

import { CreateEvaluationCommandRoutingResolveService } from './create-evaluation-command-routing-resolve.service';

describe('CreateEvaluationCommand routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: CreateEvaluationCommandRoutingResolveService;
  let service: CreateEvaluationCommandService;
  let resultCreateEvaluationCommand: ICreateEvaluationCommand | null | undefined;

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
    routingResolveService = TestBed.inject(CreateEvaluationCommandRoutingResolveService);
    service = TestBed.inject(CreateEvaluationCommandService);
    resultCreateEvaluationCommand = undefined;
  });

  describe('resolve', () => {
    it('should return ICreateEvaluationCommand returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCreateEvaluationCommand = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultCreateEvaluationCommand).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCreateEvaluationCommand = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultCreateEvaluationCommand).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<ICreateEvaluationCommand>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCreateEvaluationCommand = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultCreateEvaluationCommand).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
