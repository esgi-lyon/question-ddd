import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AnswerSubmittedEventDetailComponent } from './answer-submitted-event-detail.component';

describe('AnswerSubmittedEvent Management Detail Component', () => {
  let comp: AnswerSubmittedEventDetailComponent;
  let fixture: ComponentFixture<AnswerSubmittedEventDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AnswerSubmittedEventDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ answerSubmittedEvent: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(AnswerSubmittedEventDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AnswerSubmittedEventDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load answerSubmittedEvent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.answerSubmittedEvent).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
