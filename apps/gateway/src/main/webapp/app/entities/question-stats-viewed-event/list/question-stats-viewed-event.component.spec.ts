import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { QuestionStatsViewedEventService } from '../service/question-stats-viewed-event.service';

import { QuestionStatsViewedEventComponent } from './question-stats-viewed-event.component';

describe('QuestionStatsViewedEvent Management Component', () => {
  let comp: QuestionStatsViewedEventComponent;
  let fixture: ComponentFixture<QuestionStatsViewedEventComponent>;
  let service: QuestionStatsViewedEventService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'question-stats-viewed-event', component: QuestionStatsViewedEventComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [QuestionStatsViewedEventComponent],
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
      .overrideTemplate(QuestionStatsViewedEventComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(QuestionStatsViewedEventComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(QuestionStatsViewedEventService);

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
    expect(comp.questionStatsViewedEvents?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to questionStatsViewedEventService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getQuestionStatsViewedEventIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getQuestionStatsViewedEventIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
