import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { QuestionSentTagIdDetailComponent } from './question-sent-tag-id-detail.component';

describe('QuestionSentTagId Management Detail Component', () => {
  let comp: QuestionSentTagIdDetailComponent;
  let fixture: ComponentFixture<QuestionSentTagIdDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuestionSentTagIdDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ questionSentTagId: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(QuestionSentTagIdDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(QuestionSentTagIdDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load questionSentTagId on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.questionSentTagId).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
