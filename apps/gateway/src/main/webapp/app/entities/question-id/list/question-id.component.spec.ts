import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { QuestionIdService } from '../service/question-id.service';

import { QuestionIdComponent } from './question-id.component';

describe('QuestionId Management Component', () => {
  let comp: QuestionIdComponent;
  let fixture: ComponentFixture<QuestionIdComponent>;
  let service: QuestionIdService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'question-id', component: QuestionIdComponent }]), HttpClientTestingModule],
      declarations: [QuestionIdComponent],
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
      .overrideTemplate(QuestionIdComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(QuestionIdComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(QuestionIdService);

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
    expect(comp.questionIds?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to questionIdService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getQuestionIdIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getQuestionIdIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
