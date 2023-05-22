import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EvaluationCreatedEventDetailComponent } from './evaluation-created-event-detail.component';

describe('EvaluationCreatedEvent Management Detail Component', () => {
  let comp: EvaluationCreatedEventDetailComponent;
  let fixture: ComponentFixture<EvaluationCreatedEventDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EvaluationCreatedEventDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ evaluationCreatedEvent: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(EvaluationCreatedEventDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(EvaluationCreatedEventDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load evaluationCreatedEvent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.evaluationCreatedEvent).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
