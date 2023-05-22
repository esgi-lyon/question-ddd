import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CreateResourceCommandDetailComponent } from './create-resource-command-detail.component';

describe('CreateResourceCommand Management Detail Component', () => {
  let comp: CreateResourceCommandDetailComponent;
  let fixture: ComponentFixture<CreateResourceCommandDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateResourceCommandDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ createResourceCommand: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(CreateResourceCommandDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(CreateResourceCommandDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load createResourceCommand on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.createResourceCommand).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
