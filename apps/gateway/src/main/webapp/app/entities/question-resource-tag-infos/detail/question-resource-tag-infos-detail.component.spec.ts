import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { QuestionResourceTagInfosDetailComponent } from './question-resource-tag-infos-detail.component';

describe('QuestionResourceTagInfos Management Detail Component', () => {
  let comp: QuestionResourceTagInfosDetailComponent;
  let fixture: ComponentFixture<QuestionResourceTagInfosDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuestionResourceTagInfosDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ questionResourceTagInfos: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(QuestionResourceTagInfosDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(QuestionResourceTagInfosDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load questionResourceTagInfos on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.questionResourceTagInfos).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
