import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { AnswerSubmittedEventService } from '../service/answer-submitted-event.service';

import { AnswerSubmittedEventComponent } from './answer-submitted-event.component';

describe('AnswerSubmittedEvent Management Component', () => {
  let comp: AnswerSubmittedEventComponent;
  let fixture: ComponentFixture<AnswerSubmittedEventComponent>;
  let service: AnswerSubmittedEventService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'answer-submitted-event', component: AnswerSubmittedEventComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [AnswerSubmittedEventComponent],
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
      .overrideTemplate(AnswerSubmittedEventComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AnswerSubmittedEventComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AnswerSubmittedEventService);

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
    expect(comp.answerSubmittedEvents?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to answerSubmittedEventService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getAnswerSubmittedEventIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getAnswerSubmittedEventIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
