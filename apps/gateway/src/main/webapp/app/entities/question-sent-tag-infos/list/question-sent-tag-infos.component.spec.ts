import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { QuestionSentTagInfosService } from '../service/question-sent-tag-infos.service';

import { QuestionSentTagInfosComponent } from './question-sent-tag-infos.component';

describe('QuestionSentTagInfos Management Component', () => {
  let comp: QuestionSentTagInfosComponent;
  let fixture: ComponentFixture<QuestionSentTagInfosComponent>;
  let service: QuestionSentTagInfosService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'question-sent-tag-infos', component: QuestionSentTagInfosComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [QuestionSentTagInfosComponent],
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
      .overrideTemplate(QuestionSentTagInfosComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(QuestionSentTagInfosComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(QuestionSentTagInfosService);

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
    expect(comp.questionSentTagInfos?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to questionSentTagInfosService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getQuestionSentTagInfosIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getQuestionSentTagInfosIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
