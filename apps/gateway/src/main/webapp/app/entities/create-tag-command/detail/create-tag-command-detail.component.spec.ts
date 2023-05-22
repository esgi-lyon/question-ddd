import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CreateTagCommandDetailComponent } from './create-tag-command-detail.component';

describe('CreateTagCommand Management Detail Component', () => {
  let comp: CreateTagCommandDetailComponent;
  let fixture: ComponentFixture<CreateTagCommandDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateTagCommandDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ createTagCommand: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(CreateTagCommandDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(CreateTagCommandDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load createTagCommand on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.createTagCommand).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
