import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ViewStatsCommandDetailComponent } from './view-stats-command-detail.component';

describe('ViewStatsCommand Management Detail Component', () => {
  let comp: ViewStatsCommandDetailComponent;
  let fixture: ComponentFixture<ViewStatsCommandDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewStatsCommandDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ viewStatsCommand: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(ViewStatsCommandDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ViewStatsCommandDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load viewStatsCommand on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.viewStatsCommand).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
