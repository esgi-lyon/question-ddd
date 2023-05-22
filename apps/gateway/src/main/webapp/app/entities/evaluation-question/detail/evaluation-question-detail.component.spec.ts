import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EvaluationQuestionDetailComponent } from './evaluation-question-detail.component';

describe('EvaluationQuestion Management Detail Component', () => {
  let comp: EvaluationQuestionDetailComponent;
  let fixture: ComponentFixture<EvaluationQuestionDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EvaluationQuestionDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ evaluationQuestion: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(EvaluationQuestionDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(EvaluationQuestionDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load evaluationQuestion on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.evaluationQuestion).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
