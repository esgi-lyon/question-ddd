import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { QuestionSentTagInfosDetailComponent } from './question-sent-tag-infos-detail.component';

describe('QuestionSentTagInfos Management Detail Component', () => {
  let comp: QuestionSentTagInfosDetailComponent;
  let fixture: ComponentFixture<QuestionSentTagInfosDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuestionSentTagInfosDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ questionSentTagInfos: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(QuestionSentTagInfosDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(QuestionSentTagInfosDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load questionSentTagInfos on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.questionSentTagInfos).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
