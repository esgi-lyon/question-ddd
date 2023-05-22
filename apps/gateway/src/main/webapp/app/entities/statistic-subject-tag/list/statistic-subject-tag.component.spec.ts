import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { StatisticSubjectTagService } from '../service/statistic-subject-tag.service';

import { StatisticSubjectTagComponent } from './statistic-subject-tag.component';

describe('StatisticSubjectTag Management Component', () => {
  let comp: StatisticSubjectTagComponent;
  let fixture: ComponentFixture<StatisticSubjectTagComponent>;
  let service: StatisticSubjectTagService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'statistic-subject-tag', component: StatisticSubjectTagComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [StatisticSubjectTagComponent],
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
      .overrideTemplate(StatisticSubjectTagComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(StatisticSubjectTagComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(StatisticSubjectTagService);

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
    expect(comp.statisticSubjectTags?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to statisticSubjectTagService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getStatisticSubjectTagIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getStatisticSubjectTagIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
