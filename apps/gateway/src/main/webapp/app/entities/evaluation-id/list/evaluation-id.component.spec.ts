import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { EvaluationIdService } from '../service/evaluation-id.service';

import { EvaluationIdComponent } from './evaluation-id.component';

describe('EvaluationId Management Component', () => {
  let comp: EvaluationIdComponent;
  let fixture: ComponentFixture<EvaluationIdComponent>;
  let service: EvaluationIdService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'evaluation-id', component: EvaluationIdComponent }]), HttpClientTestingModule],
      declarations: [EvaluationIdComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              })
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(EvaluationIdComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(EvaluationIdComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(EvaluationIdService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.evaluationIds?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to evaluationIdService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getEvaluationIdIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getEvaluationIdIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
