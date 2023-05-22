import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AnsweredTagDetailComponent } from './answered-tag-detail.component';

describe('AnsweredTag Management Detail Component', () => {
  let comp: AnsweredTagDetailComponent;
  let fixture: ComponentFixture<AnsweredTagDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AnsweredTagDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ answeredTag: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(AnsweredTagDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AnsweredTagDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load answeredTag on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.answeredTag).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
