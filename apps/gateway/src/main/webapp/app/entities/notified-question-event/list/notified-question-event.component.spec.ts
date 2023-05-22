import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { NotifiedQuestionEventService } from '../service/notified-question-event.service';

import { NotifiedQuestionEventComponent } from './notified-question-event.component';

describe('NotifiedQuestionEvent Management Component', () => {
  let comp: NotifiedQuestionEventComponent;
  let fixture: ComponentFixture<NotifiedQuestionEventComponent>;
  let service: NotifiedQuestionEventService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'notified-question-event', component: NotifiedQuestionEventComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [NotifiedQuestionEventComponent],
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
      .overrideTemplate(NotifiedQuestionEventComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(NotifiedQuestionEventComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(NotifiedQuestionEventService);

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
    expect(comp.notifiedQuestionEvents?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to notifiedQuestionEventService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getNotifiedQuestionEventIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getNotifiedQuestionEventIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
