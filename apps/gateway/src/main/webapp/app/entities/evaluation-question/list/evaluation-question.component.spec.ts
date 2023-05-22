import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { EvaluationQuestionService } from '../service/evaluation-question.service';

import { EvaluationQuestionComponent } from './evaluation-question.component';

describe('EvaluationQuestion Management Component', () => {
  let comp: EvaluationQuestionComponent;
  let fixture: ComponentFixture<EvaluationQuestionComponent>;
  let service: EvaluationQuestionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'evaluation-question', component: EvaluationQuestionComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [EvaluationQuestionComponent],
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
      .overrideTemplate(EvaluationQuestionComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(EvaluationQuestionComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(EvaluationQuestionService);

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
    expect(comp.evaluationQuestions?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to evaluationQuestionService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getEvaluationQuestionIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getEvaluationQuestionIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
