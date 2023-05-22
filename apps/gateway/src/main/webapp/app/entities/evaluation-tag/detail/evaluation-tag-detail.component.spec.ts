import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EvaluationTagDetailComponent } from './evaluation-tag-detail.component';

describe('EvaluationTag Management Detail Component', () => {
  let comp: EvaluationTagDetailComponent;
  let fixture: ComponentFixture<EvaluationTagDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EvaluationTagDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ evaluationTag: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(EvaluationTagDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(EvaluationTagDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load evaluationTag on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.evaluationTag).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
