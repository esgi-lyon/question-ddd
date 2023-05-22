import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { QuestionIdDetailComponent } from './question-id-detail.component';

describe('QuestionId Management Detail Component', () => {
  let comp: QuestionIdDetailComponent;
  let fixture: ComponentFixture<QuestionIdDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuestionIdDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ questionId: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(QuestionIdDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(QuestionIdDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load questionId on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.questionId).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
