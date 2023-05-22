import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TagChoicesListCommandDetailComponent } from './tag-choices-list-command-detail.component';

describe('TagChoicesListCommand Management Detail Component', () => {
  let comp: TagChoicesListCommandDetailComponent;
  let fixture: ComponentFixture<TagChoicesListCommandDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TagChoicesListCommandDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ tagChoicesListCommand: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(TagChoicesListCommandDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(TagChoicesListCommandDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load tagChoicesListCommand on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.tagChoicesListCommand).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
