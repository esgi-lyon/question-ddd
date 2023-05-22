import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { EvaluationCreatedEventService } from '../service/evaluation-created-event.service';

import { EvaluationCreatedEventComponent } from './evaluation-created-event.component';

describe('EvaluationCreatedEvent Management Component', () => {
  let comp: EvaluationCreatedEventComponent;
  let fixture: ComponentFixture<EvaluationCreatedEventComponent>;
  let service: EvaluationCreatedEventService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'evaluation-created-event', component: EvaluationCreatedEventComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [EvaluationCreatedEventComponent],
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
      .overrideTemplate(EvaluationCreatedEventComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(EvaluationCreatedEventComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(EvaluationCreatedEventService);

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
    expect(comp.evaluationCreatedEvents?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to evaluationCreatedEventService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getEvaluationCreatedEventIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getEvaluationCreatedEventIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
