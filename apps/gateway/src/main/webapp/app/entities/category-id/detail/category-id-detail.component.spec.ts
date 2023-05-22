import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CategoryIdDetailComponent } from './category-id-detail.component';

describe('CategoryId Management Detail Component', () => {
  let comp: CategoryIdDetailComponent;
  let fixture: ComponentFixture<CategoryIdDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CategoryIdDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ categoryId: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(CategoryIdDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(CategoryIdDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load categoryId on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.categoryId).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
