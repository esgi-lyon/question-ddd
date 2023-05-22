import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TagInfosDetailComponent } from './tag-infos-detail.component';

describe('TagInfos Management Detail Component', () => {
  let comp: TagInfosDetailComponent;
  let fixture: ComponentFixture<TagInfosDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TagInfosDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ tagInfos: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(TagInfosDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(TagInfosDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load tagInfos on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.tagInfos).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
