import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UserStatsViewedEventDetailComponent } from './user-stats-viewed-event-detail.component';

describe('UserStatsViewedEvent Management Detail Component', () => {
  let comp: UserStatsViewedEventDetailComponent;
  let fixture: ComponentFixture<UserStatsViewedEventDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserStatsViewedEventDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ userStatsViewedEvent: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(UserStatsViewedEventDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(UserStatsViewedEventDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load userStatsViewedEvent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.userStatsViewedEvent).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
