import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { QuestionStatsViewedEventDetailComponent } from './question-stats-viewed-event-detail.component';

describe('QuestionStatsViewedEvent Management Detail Component', () => {
  let comp: QuestionStatsViewedEventDetailComponent;
  let fixture: ComponentFixture<QuestionStatsViewedEventDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuestionStatsViewedEventDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ questionStatsViewedEvent: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(QuestionStatsViewedEventDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(QuestionStatsViewedEventDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load questionStatsViewedEvent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.questionStatsViewedEvent).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
