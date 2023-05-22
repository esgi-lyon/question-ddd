import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { EvaluationService } from '../service/evaluation.service';

import { EvaluationComponent } from './evaluation.component';

describe('Evaluation Management Component', () => {
  let comp: EvaluationComponent;
  let fixture: ComponentFixture<EvaluationComponent>;
  let service: EvaluationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'evaluation', component: EvaluationComponent }]), HttpClientTestingModule],
      declarations: [EvaluationComponent],
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
      .overrideTemplate(EvaluationComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(EvaluationComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(EvaluationService);

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
    expect(comp.evaluations?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to evaluationService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getEvaluationIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getEvaluationIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
