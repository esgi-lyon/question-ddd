import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TagCreatedEventDetailComponent } from './tag-created-event-detail.component';

describe('TagCreatedEvent Management Detail Component', () => {
  let comp: TagCreatedEventDetailComponent;
  let fixture: ComponentFixture<TagCreatedEventDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TagCreatedEventDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ tagCreatedEvent: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(TagCreatedEventDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(TagCreatedEventDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load tagCreatedEvent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.tagCreatedEvent).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
