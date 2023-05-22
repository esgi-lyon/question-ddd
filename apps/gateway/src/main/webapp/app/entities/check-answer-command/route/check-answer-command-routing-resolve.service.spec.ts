import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ICheckAnswerCommand } from '../check-answer-command.model';
import { CheckAnswerCommandService } from '../service/check-answer-command.service';

import { CheckAnswerCommandRoutingResolveService } from './check-answer-command-routing-resolve.service';

describe('CheckAnswerCommand routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: CheckAnswerCommandRoutingResolveService;
  let service: CheckAnswerCommandService;
  let resultCheckAnswerCommand: ICheckAnswerCommand | null | undefined;

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
    routingResolveService = TestBed.inject(CheckAnswerCommandRoutingResolveService);
    service = TestBed.inject(CheckAnswerCommandService);
    resultCheckAnswerCommand = undefined;
  });

  describe('resolve', () => {
    it('should return ICheckAnswerCommand returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCheckAnswerCommand = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultCheckAnswerCommand).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCheckAnswerCommand = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultCheckAnswerCommand).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<ICheckAnswerCommand>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultCheckAnswerCommand = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultCheckAnswerCommand).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
