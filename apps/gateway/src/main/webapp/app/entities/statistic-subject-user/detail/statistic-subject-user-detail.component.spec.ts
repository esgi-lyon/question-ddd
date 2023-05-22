import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StatisticSubjectUserDetailComponent } from './statistic-subject-user-detail.component';

describe('StatisticSubjectUser Management Detail Component', () => {
  let comp: StatisticSubjectUserDetailComponent;
  let fixture: ComponentFixture<StatisticSubjectUserDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StatisticSubjectUserDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ statisticSubjectUser: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(StatisticSubjectUserDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(StatisticSubjectUserDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load statisticSubjectUser on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.statisticSubjectUser).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
