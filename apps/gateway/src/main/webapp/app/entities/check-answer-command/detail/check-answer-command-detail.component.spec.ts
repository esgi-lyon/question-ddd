import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CheckAnswerCommandDetailComponent } from './check-answer-command-detail.component';

describe('CheckAnswerCommand Management Detail Component', () => {
  let comp: CheckAnswerCommandDetailComponent;
  let fixture: ComponentFixture<CheckAnswerCommandDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CheckAnswerCommandDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ checkAnswerCommand: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(CheckAnswerCommandDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(CheckAnswerCommandDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load checkAnswerCommand on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.checkAnswerCommand).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
