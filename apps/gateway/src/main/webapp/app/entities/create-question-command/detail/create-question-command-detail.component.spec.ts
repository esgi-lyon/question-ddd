import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CreateQuestionCommandDetailComponent } from './create-question-command-detail.component';

describe('CreateQuestionCommand Management Detail Component', () => {
  let comp: CreateQuestionCommandDetailComponent;
  let fixture: ComponentFixture<CreateQuestionCommandDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateQuestionCommandDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ createQuestionCommand: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(CreateQuestionCommandDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(CreateQuestionCommandDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load createQuestionCommand on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.createQuestionCommand).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
