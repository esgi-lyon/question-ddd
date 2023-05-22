import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StatisticSubjectTagDetailComponent } from './statistic-subject-tag-detail.component';

describe('StatisticSubjectTag Management Detail Component', () => {
  let comp: StatisticSubjectTagDetailComponent;
  let fixture: ComponentFixture<StatisticSubjectTagDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StatisticSubjectTagDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ statisticSubjectTag: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(StatisticSubjectTagDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(StatisticSubjectTagDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load statisticSubjectTag on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.statisticSubjectTag).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
