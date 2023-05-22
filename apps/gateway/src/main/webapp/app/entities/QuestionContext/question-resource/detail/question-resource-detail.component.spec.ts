import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { QuestionResourceDetailComponent } from './question-resource-detail.component';

describe('QuestionResource Management Detail Component', () => {
  let comp: QuestionResourceDetailComponent;
  let fixture: ComponentFixture<QuestionResourceDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuestionResourceDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ questionResource: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(QuestionResourceDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(QuestionResourceDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load questionResource on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.questionResource).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
