import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { QuestionResourceService } from '../service/question-resource.service';

import { QuestionResourceComponent } from './question-resource.component';

describe('QuestionResource Management Component', () => {
  let comp: QuestionResourceComponent;
  let fixture: ComponentFixture<QuestionResourceComponent>;
  let service: QuestionResourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'question-resource', component: QuestionResourceComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [QuestionResourceComponent],
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
      .overrideTemplate(QuestionResourceComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(QuestionResourceComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(QuestionResourceService);

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
    expect(comp.questionResources?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to questionResourceService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getQuestionResourceIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getQuestionResourceIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
