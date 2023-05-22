import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { StatisticSubjectQuestionService } from '../service/statistic-subject-question.service';

import { StatisticSubjectQuestionComponent } from './statistic-subject-question.component';

describe('StatisticSubjectQuestion Management Component', () => {
  let comp: StatisticSubjectQuestionComponent;
  let fixture: ComponentFixture<StatisticSubjectQuestionComponent>;
  let service: StatisticSubjectQuestionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'statistic-subject-question', component: StatisticSubjectQuestionComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [StatisticSubjectQuestionComponent],
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
      .overrideTemplate(StatisticSubjectQuestionComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(StatisticSubjectQuestionComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(StatisticSubjectQuestionService);

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
    expect(comp.statisticSubjectQuestions?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to statisticSubjectQuestionService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getStatisticSubjectQuestionIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getStatisticSubjectQuestionIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
