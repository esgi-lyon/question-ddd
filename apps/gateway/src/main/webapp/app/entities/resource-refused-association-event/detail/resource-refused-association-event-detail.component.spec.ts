import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ResourceRefusedAssociationEventDetailComponent } from './resource-refused-association-event-detail.component';

describe('ResourceRefusedAssociationEvent Management Detail Component', () => {
  let comp: ResourceRefusedAssociationEventDetailComponent;
  let fixture: ComponentFixture<ResourceRefusedAssociationEventDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResourceRefusedAssociationEventDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ resourceRefusedAssociationEvent: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(ResourceRefusedAssociationEventDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ResourceRefusedAssociationEventDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load resourceRefusedAssociationEvent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.resourceRefusedAssociationEvent).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
