import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AwardPointForEvaluationCommandDetailComponent } from './award-point-for-evaluation-command-detail.component';

describe('AwardPointForEvaluationCommand Management Detail Component', () => {
  let comp: AwardPointForEvaluationCommandDetailComponent;
  let fixture: ComponentFixture<AwardPointForEvaluationCommandDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AwardPointForEvaluationCommandDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ awardPointForEvaluationCommand: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(AwardPointForEvaluationCommandDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AwardPointForEvaluationCommandDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load awardPointForEvaluationCommand on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.awardPointForEvaluationCommand).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
