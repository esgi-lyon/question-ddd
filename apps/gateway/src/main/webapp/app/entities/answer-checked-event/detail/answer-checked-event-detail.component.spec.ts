import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AnswerCheckedEventDetailComponent } from './answer-checked-event-detail.component';

describe('AnswerCheckedEvent Management Detail Component', () => {
  let comp: AnswerCheckedEventDetailComponent;
  let fixture: ComponentFixture<AnswerCheckedEventDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AnswerCheckedEventDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ answerCheckedEvent: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(AnswerCheckedEventDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AnswerCheckedEventDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load answerCheckedEvent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.answerCheckedEvent).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
