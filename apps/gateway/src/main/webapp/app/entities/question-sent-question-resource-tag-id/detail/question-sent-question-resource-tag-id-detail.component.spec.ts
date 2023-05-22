import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { QuestionSentQuestionResourceTagIdDetailComponent } from './question-sent-question-resource-tag-id-detail.component';

describe('QuestionSentQuestionResourceTagId Management Detail Component', () => {
  let comp: QuestionSentQuestionResourceTagIdDetailComponent;
  let fixture: ComponentFixture<QuestionSentQuestionResourceTagIdDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuestionSentQuestionResourceTagIdDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ questionSentQuestionResourceTagId: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(QuestionSentQuestionResourceTagIdDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(QuestionSentQuestionResourceTagIdDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load questionSentQuestionResourceTagId on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.questionSentQuestionResourceTagId).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
