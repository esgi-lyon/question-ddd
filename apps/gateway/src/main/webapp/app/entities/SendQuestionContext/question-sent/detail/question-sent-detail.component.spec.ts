import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { QuestionSentDetailComponent } from './question-sent-detail.component';

describe('QuestionSent Management Detail Component', () => {
  let comp: QuestionSentDetailComponent;
  let fixture: ComponentFixture<QuestionSentDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuestionSentDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ questionSent: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(QuestionSentDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(QuestionSentDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load questionSent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.questionSent).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
