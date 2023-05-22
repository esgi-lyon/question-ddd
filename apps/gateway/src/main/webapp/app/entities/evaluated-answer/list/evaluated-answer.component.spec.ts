import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { EvaluatedAnswerService } from '../service/evaluated-answer.service';

import { EvaluatedAnswerComponent } from './evaluated-answer.component';

describe('EvaluatedAnswer Management Component', () => {
  let comp: EvaluatedAnswerComponent;
  let fixture: ComponentFixture<EvaluatedAnswerComponent>;
  let service: EvaluatedAnswerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'evaluated-answer', component: EvaluatedAnswerComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [EvaluatedAnswerComponent],
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
      .overrideTemplate(EvaluatedAnswerComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(EvaluatedAnswerComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(EvaluatedAnswerService);

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
    expect(comp.evaluatedAnswers?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to evaluatedAnswerService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getEvaluatedAnswerIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getEvaluatedAnswerIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
