import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { TagChoicesListedEventService } from '../service/tag-choices-listed-event.service';

import { TagChoicesListedEventComponent } from './tag-choices-listed-event.component';

describe('TagChoicesListedEvent Management Component', () => {
  let comp: TagChoicesListedEventComponent;
  let fixture: ComponentFixture<TagChoicesListedEventComponent>;
  let service: TagChoicesListedEventService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'tag-choices-listed-event', component: TagChoicesListedEventComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [TagChoicesListedEventComponent],
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
      .overrideTemplate(TagChoicesListedEventComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TagChoicesListedEventComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(TagChoicesListedEventService);

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
    expect(comp.tagChoicesListedEvents?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to tagChoicesListedEventService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getTagChoicesListedEventIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getTagChoicesListedEventIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
