import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ResourceAcceptedAssociationEventDetailComponent } from './resource-accepted-association-event-detail.component';

describe('ResourceAcceptedAssociationEvent Management Detail Component', () => {
  let comp: ResourceAcceptedAssociationEventDetailComponent;
  let fixture: ComponentFixture<ResourceAcceptedAssociationEventDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResourceAcceptedAssociationEventDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ resourceAcceptedAssociationEvent: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(ResourceAcceptedAssociationEventDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ResourceAcceptedAssociationEventDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load resourceAcceptedAssociationEvent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.resourceAcceptedAssociationEvent).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
