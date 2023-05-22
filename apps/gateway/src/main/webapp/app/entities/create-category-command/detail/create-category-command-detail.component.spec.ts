import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CreateCategoryCommandDetailComponent } from './create-category-command-detail.component';

describe('CreateCategoryCommand Management Detail Component', () => {
  let comp: CreateCategoryCommandDetailComponent;
  let fixture: ComponentFixture<CreateCategoryCommandDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateCategoryCommandDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ createCategoryCommand: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(CreateCategoryCommandDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(CreateCategoryCommandDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load createCategoryCommand on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.createCategoryCommand).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
