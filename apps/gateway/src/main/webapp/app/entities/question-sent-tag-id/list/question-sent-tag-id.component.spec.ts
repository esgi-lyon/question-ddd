import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { QuestionSentTagIdService } from '../service/question-sent-tag-id.service';

import { QuestionSentTagIdComponent } from './question-sent-tag-id.component';

describe('QuestionSentTagId Management Component', () => {
  let comp: QuestionSentTagIdComponent;
  let fixture: ComponentFixture<QuestionSentTagIdComponent>;
  let service: QuestionSentTagIdService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'question-sent-tag-id', component: QuestionSentTagIdComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [QuestionSentTagIdComponent],
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
      .overrideTemplate(QuestionSentTagIdComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(QuestionSentTagIdComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(QuestionSentTagIdService);

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
    expect(comp.questionSentTagIds?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to questionSentTagIdService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getQuestionSentTagIdIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getQuestionSentTagIdIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
