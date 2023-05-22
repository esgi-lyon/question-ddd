import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { TagStatsViewedEventService } from '../service/tag-stats-viewed-event.service';

import { TagStatsViewedEventComponent } from './tag-stats-viewed-event.component';

describe('TagStatsViewedEvent Management Component', () => {
  let comp: TagStatsViewedEventComponent;
  let fixture: ComponentFixture<TagStatsViewedEventComponent>;
  let service: TagStatsViewedEventService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'tag-stats-viewed-event', component: TagStatsViewedEventComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [TagStatsViewedEventComponent],
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
      .overrideTemplate(TagStatsViewedEventComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TagStatsViewedEventComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(TagStatsViewedEventService);

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
    expect(comp.tagStatsViewedEvents?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to tagStatsViewedEventService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getTagStatsViewedEventIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getTagStatsViewedEventIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
