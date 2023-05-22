import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CreateEvaluationCommandDetailComponent } from './create-evaluation-command-detail.component';

describe('CreateEvaluationCommand Management Detail Component', () => {
  let comp: CreateEvaluationCommandDetailComponent;
  let fixture: ComponentFixture<CreateEvaluationCommandDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateEvaluationCommandDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ createEvaluationCommand: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(CreateEvaluationCommandDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(CreateEvaluationCommandDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load createEvaluationCommand on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.createEvaluationCommand).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
