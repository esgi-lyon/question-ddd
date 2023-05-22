import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TagChoicesListedEventDetailComponent } from './tag-choices-listed-event-detail.component';

describe('TagChoicesListedEvent Management Detail Component', () => {
  let comp: TagChoicesListedEventDetailComponent;
  let fixture: ComponentFixture<TagChoicesListedEventDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TagChoicesListedEventDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ tagChoicesListedEvent: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(TagChoicesListedEventDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(TagChoicesListedEventDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load tagChoicesListedEvent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.tagChoicesListedEvent).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
