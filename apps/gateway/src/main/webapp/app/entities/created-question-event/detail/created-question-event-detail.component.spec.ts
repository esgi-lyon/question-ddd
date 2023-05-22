import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CreatedQuestionEventDetailComponent } from './created-question-event-detail.component';

describe('CreatedQuestionEvent Management Detail Component', () => {
  let comp: CreatedQuestionEventDetailComponent;
  let fixture: ComponentFixture<CreatedQuestionEventDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreatedQuestionEventDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ createdQuestionEvent: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(CreatedQuestionEventDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(CreatedQuestionEventDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load createdQuestionEvent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.createdQuestionEvent).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
