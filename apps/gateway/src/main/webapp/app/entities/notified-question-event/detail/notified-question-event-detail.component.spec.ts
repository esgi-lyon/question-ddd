import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NotifiedQuestionEventDetailComponent } from './notified-question-event-detail.component';

describe('NotifiedQuestionEvent Management Detail Component', () => {
  let comp: NotifiedQuestionEventDetailComponent;
  let fixture: ComponentFixture<NotifiedQuestionEventDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NotifiedQuestionEventDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ notifiedQuestionEvent: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(NotifiedQuestionEventDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(NotifiedQuestionEventDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load notifiedQuestionEvent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.notifiedQuestionEvent).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
