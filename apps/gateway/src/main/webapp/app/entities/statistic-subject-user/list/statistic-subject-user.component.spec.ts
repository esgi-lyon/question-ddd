import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { StatisticSubjectUserService } from '../service/statistic-subject-user.service';

import { StatisticSubjectUserComponent } from './statistic-subject-user.component';

describe('StatisticSubjectUser Management Component', () => {
  let comp: StatisticSubjectUserComponent;
  let fixture: ComponentFixture<StatisticSubjectUserComponent>;
  let service: StatisticSubjectUserService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'statistic-subject-user', component: StatisticSubjectUserComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [StatisticSubjectUserComponent],
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
      .overrideTemplate(StatisticSubjectUserComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(StatisticSubjectUserComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(StatisticSubjectUserService);

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
    expect(comp.statisticSubjectUsers?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to statisticSubjectUserService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getStatisticSubjectUserIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getStatisticSubjectUserIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
