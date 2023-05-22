import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ICreateQuestionCommand } from '../create-question-command.model';
import { CreateQuestionCommandService } from '../service/create-question-command.service';

import { CreateQuestionCommandRoutingResolveService } from './create-question-command-routing-resolve.service';

describe('CreateQuestionCommand routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: CreateQuestionCommandRoutingResolveService;
  let service: CreateQuestionCommandService;
  let resultCreateQuestionCommand: ICreateQuestionCommand | null | undefined;

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
    routingResolveService = TestBed.inject(CreateQuestionCommandRoutingResolveService);
    service = TestBed.inject(CreateQuestionCommandService);
    resultCreateQuestionCommand = undefined;
  });

  describe('resolve', () => {
    it('should return ICreateQuestionCommand returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCreateQuestionCommand = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultCreateQuestionCommand).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCreateQuestionCommand = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultCreateQuestionCommand).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<ICreateQuestionCommand>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCreateQuestionCommand = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultCreateQuestionCommand).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
