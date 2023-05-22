import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StatisticSubjectQuestionDetailComponent } from './statistic-subject-question-detail.component';

describe('StatisticSubjectQuestion Management Detail Component', () => {
  let comp: StatisticSubjectQuestionDetailComponent;
  let fixture: ComponentFixture<StatisticSubjectQuestionDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StatisticSubjectQuestionDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ statisticSubjectQuestion: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(StatisticSubjectQuestionDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(StatisticSubjectQuestionDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load statisticSubjectQuestion on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.statisticSubjectQuestion).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
