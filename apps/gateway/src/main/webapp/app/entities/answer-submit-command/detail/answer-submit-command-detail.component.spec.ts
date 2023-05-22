import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AnswerSubmitCommandDetailComponent } from './answer-submit-command-detail.component';

describe('AnswerSubmitCommand Management Detail Component', () => {
  let comp: AnswerSubmitCommandDetailComponent;
  let fixture: ComponentFixture<AnswerSubmitCommandDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AnswerSubmitCommandDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ answerSubmitCommand: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(AnswerSubmitCommandDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AnswerSubmitCommandDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load answerSubmitCommand on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.answerSubmitCommand).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
