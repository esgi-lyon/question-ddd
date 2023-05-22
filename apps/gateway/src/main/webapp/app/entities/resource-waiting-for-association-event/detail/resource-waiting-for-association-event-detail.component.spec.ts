import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ResourceWaitingForAssociationEventDetailComponent } from './resource-waiting-for-association-event-detail.component';

describe('ResourceWaitingForAssociationEvent Management Detail Component', () => {
  let comp: ResourceWaitingForAssociationEventDetailComponent;
  let fixture: ComponentFixture<ResourceWaitingForAssociationEventDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResourceWaitingForAssociationEventDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ resourceWaitingForAssociationEvent: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(ResourceWaitingForAssociationEventDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ResourceWaitingForAssociationEventDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load resourceWaitingForAssociationEvent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.resourceWaitingForAssociationEvent).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
