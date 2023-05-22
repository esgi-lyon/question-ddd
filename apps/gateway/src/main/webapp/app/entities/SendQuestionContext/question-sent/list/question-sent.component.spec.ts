import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { QuestionSentService } from '../service/question-sent.service';

import { QuestionSentComponent } from './question-sent.component';

describe('QuestionSent Management Component', () => {
  let comp: QuestionSentComponent;
  let fixture: ComponentFixture<QuestionSentComponent>;
  let service: QuestionSentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'question-sent', component: QuestionSentComponent }]), HttpClientTestingModule],
      declarations: [QuestionSentComponent],
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
      .overrideTemplate(QuestionSentComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(QuestionSentComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(QuestionSentService);

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
    expect(comp.questionSents?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to questionSentService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getQuestionSentIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getQuestionSentIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
