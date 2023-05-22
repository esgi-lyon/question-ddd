import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RejectResourceTagCommandDetailComponent } from './reject-resource-tag-command-detail.component';

describe('RejectResourceTagCommand Management Detail Component', () => {
  let comp: RejectResourceTagCommandDetailComponent;
  let fixture: ComponentFixture<RejectResourceTagCommandDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RejectResourceTagCommandDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ rejectResourceTagCommand: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(RejectResourceTagCommandDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(RejectResourceTagCommandDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load rejectResourceTagCommand on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.rejectResourceTagCommand).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
