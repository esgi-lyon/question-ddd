import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { QuestionResourceTagInfosService } from '../service/question-resource-tag-infos.service';

import { QuestionResourceTagInfosComponent } from './question-resource-tag-infos.component';

describe('QuestionResourceTagInfos Management Component', () => {
  let comp: QuestionResourceTagInfosComponent;
  let fixture: ComponentFixture<QuestionResourceTagInfosComponent>;
  let service: QuestionResourceTagInfosService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'question-resource-tag-infos', component: QuestionResourceTagInfosComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [QuestionResourceTagInfosComponent],
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
      .overrideTemplate(QuestionResourceTagInfosComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(QuestionResourceTagInfosComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(QuestionResourceTagInfosService);

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
    expect(comp.questionResourceTagInfos?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to questionResourceTagInfosService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getQuestionResourceTagInfosIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getQuestionResourceTagInfosIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
