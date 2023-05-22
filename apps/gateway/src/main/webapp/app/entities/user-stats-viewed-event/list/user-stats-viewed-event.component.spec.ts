import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { UserStatsViewedEventService } from '../service/user-stats-viewed-event.service';

import { UserStatsViewedEventComponent } from './user-stats-viewed-event.component';

describe('UserStatsViewedEvent Management Component', () => {
  let comp: UserStatsViewedEventComponent;
  let fixture: ComponentFixture<UserStatsViewedEventComponent>;
  let service: UserStatsViewedEventService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'user-stats-viewed-event', component: UserStatsViewedEventComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [UserStatsViewedEventComponent],
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
      .overrideTemplate(UserStatsViewedEventComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(UserStatsViewedEventComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(UserStatsViewedEventService);

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
    expect(comp.userStatsViewedEvents?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to userStatsViewedEventService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getUserStatsViewedEventIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getUserStatsViewedEventIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
