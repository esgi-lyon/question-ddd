import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { QuestionSentQuestionResourceTagIdService } from '../service/question-sent-question-resource-tag-id.service';

import { QuestionSentQuestionResourceTagIdComponent } from './question-sent-question-resource-tag-id.component';

describe('QuestionSentQuestionResourceTagId Management Component', () => {
  let comp: QuestionSentQuestionResourceTagIdComponent;
  let fixture: ComponentFixture<QuestionSentQuestionResourceTagIdComponent>;
  let service: QuestionSentQuestionResourceTagIdService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([
          { path: 'question-sent-question-resource-tag-id', component: QuestionSentQuestionResourceTagIdComponent },
        ]),
        HttpClientTestingModule,
      ],
      declarations: [QuestionSentQuestionResourceTagIdComponent],
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
      .overrideTemplate(QuestionSentQuestionResourceTagIdComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(QuestionSentQuestionResourceTagIdComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(QuestionSentQuestionResourceTagIdService);

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
    expect(comp.questionSentQuestionResourceTagIds?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to questionSentQuestionResourceTagIdService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getQuestionSentQuestionResourceTagIdIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getQuestionSentQuestionResourceTagIdIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
