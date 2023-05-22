import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TagStatsViewedEventDetailComponent } from './tag-stats-viewed-event-detail.component';

describe('TagStatsViewedEvent Management Detail Component', () => {
  let comp: TagStatsViewedEventDetailComponent;
  let fixture: ComponentFixture<TagStatsViewedEventDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TagStatsViewedEventDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ tagStatsViewedEvent: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(TagStatsViewedEventDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(TagStatsViewedEventDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load tagStatsViewedEvent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.tagStatsViewedEvent).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
