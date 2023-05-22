import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CategoryCreatedEventDetailComponent } from './category-created-event-detail.component';

describe('CategoryCreatedEvent Management Detail Component', () => {
  let comp: CategoryCreatedEventDetailComponent;
  let fixture: ComponentFixture<CategoryCreatedEventDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CategoryCreatedEventDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ categoryCreatedEvent: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(CategoryCreatedEventDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(CategoryCreatedEventDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load categoryCreatedEvent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.categoryCreatedEvent).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
