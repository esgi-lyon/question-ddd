import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EvaluationIdDetailComponent } from './evaluation-id-detail.component';

describe('EvaluationId Management Detail Component', () => {
  let comp: EvaluationIdDetailComponent;
  let fixture: ComponentFixture<EvaluationIdDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EvaluationIdDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ evaluationId: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(EvaluationIdDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(EvaluationIdDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load evaluationId on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.evaluationId).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
