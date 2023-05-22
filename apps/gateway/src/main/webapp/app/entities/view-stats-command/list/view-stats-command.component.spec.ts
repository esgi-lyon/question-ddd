import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ViewStatsCommandService } from '../service/view-stats-command.service';

import { ViewStatsCommandComponent } from './view-stats-command.component';

describe('ViewStatsCommand Management Component', () => {
  let comp: ViewStatsCommandComponent;
  let fixture: ComponentFixture<ViewStatsCommandComponent>;
  let service: ViewStatsCommandService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'view-stats-command', component: ViewStatsCommandComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [ViewStatsCommandComponent],
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
      .overrideTemplate(ViewStatsCommandComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ViewStatsCommandComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(ViewStatsCommandService);

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
    expect(comp.viewStatsCommands?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to viewStatsCommandService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getViewStatsCommandIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getViewStatsCommandIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
