import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { TagCreatedEventService } from '../service/tag-created-event.service';

import { TagCreatedEventComponent } from './tag-created-event.component';

describe('TagCreatedEvent Management Component', () => {
  let comp: TagCreatedEventComponent;
  let fixture: ComponentFixture<TagCreatedEventComponent>;
  let service: TagCreatedEventService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'tag-created-event', component: TagCreatedEventComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [TagCreatedEventComponent],
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
      .overrideTemplate(TagCreatedEventComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TagCreatedEventComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(TagCreatedEventService);

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
    expect(comp.tagCreatedEvents?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to tagCreatedEventService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getTagCreatedEventIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getTagCreatedEventIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
