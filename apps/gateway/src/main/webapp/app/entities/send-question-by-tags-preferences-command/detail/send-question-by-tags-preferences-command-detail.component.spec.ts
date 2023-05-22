import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SendQuestionByTagsPreferencesCommandDetailComponent } from './send-question-by-tags-preferences-command-detail.component';

describe('SendQuestionByTagsPreferencesCommand Management Detail Component', () => {
  let comp: SendQuestionByTagsPreferencesCommandDetailComponent;
  let fixture: ComponentFixture<SendQuestionByTagsPreferencesCommandDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SendQuestionByTagsPreferencesCommandDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ sendQuestionByTagsPreferencesCommand: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(SendQuestionByTagsPreferencesCommandDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(SendQuestionByTagsPreferencesCommandDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load sendQuestionByTagsPreferencesCommand on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.sendQuestionByTagsPreferencesCommand).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
