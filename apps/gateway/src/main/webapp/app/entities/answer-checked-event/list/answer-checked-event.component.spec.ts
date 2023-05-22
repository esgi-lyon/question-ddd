import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { AnswerCheckedEventService } from '../service/answer-checked-event.service';

import { AnswerCheckedEventComponent } from './answer-checked-event.component';

describe('AnswerCheckedEvent Management Component', () => {
  let comp: AnswerCheckedEventComponent;
  let fixture: ComponentFixture<AnswerCheckedEventComponent>;
  let service: AnswerCheckedEventService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'answer-checked-event', component: AnswerCheckedEventComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [AnswerCheckedEventComponent],
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
      .overrideTemplate(AnswerCheckedEventComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AnswerCheckedEventComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AnswerCheckedEventService);

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
    expect(comp.answerCheckedEvents?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to answerCheckedEventService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getAnswerCheckedEventIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getAnswerCheckedEventIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
