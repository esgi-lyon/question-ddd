import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AnsweringUserDetailComponent } from './answering-user-detail.component';

describe('AnsweringUser Management Detail Component', () => {
  let comp: AnsweringUserDetailComponent;
  let fixture: ComponentFixture<AnsweringUserDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AnsweringUserDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ answeringUser: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(AnsweringUserDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AnsweringUserDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load answeringUser on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.answeringUser).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
