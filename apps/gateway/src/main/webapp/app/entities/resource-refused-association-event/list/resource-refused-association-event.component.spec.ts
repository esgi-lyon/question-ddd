import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ResourceRefusedAssociationEventService } from '../service/resource-refused-association-event.service';

import { ResourceRefusedAssociationEventComponent } from './resource-refused-association-event.component';

describe('ResourceRefusedAssociationEvent Management Component', () => {
  let comp: ResourceRefusedAssociationEventComponent;
  let fixture: ComponentFixture<ResourceRefusedAssociationEventComponent>;
  let service: ResourceRefusedAssociationEventService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([
          { path: 'resource-refused-association-event', component: ResourceRefusedAssociationEventComponent },
        ]),
        HttpClientTestingModule,
      ],
      declarations: [ResourceRefusedAssociationEventComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              })
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(ResourceRefusedAssociationEventComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ResourceRefusedAssociationEventComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(ResourceRefusedAssociationEventService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.resourceRefusedAssociationEvents?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to resourceRefusedAssociationEventService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getResourceRefusedAssociationEventIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getResourceRefusedAssociationEventIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
