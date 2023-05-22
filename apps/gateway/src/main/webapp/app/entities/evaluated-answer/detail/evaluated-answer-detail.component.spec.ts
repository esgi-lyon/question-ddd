import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EvaluatedAnswerDetailComponent } from './evaluated-answer-detail.component';

describe('EvaluatedAnswer Management Detail Component', () => {
  let comp: EvaluatedAnswerDetailComponent;
  let fixture: ComponentFixture<EvaluatedAnswerDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EvaluatedAnswerDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ evaluatedAnswer: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(EvaluatedAnswerDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(EvaluatedAnswerDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load evaluatedAnswer on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.evaluatedAnswer).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
