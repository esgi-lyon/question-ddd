import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { EvaluationTagService } from '../service/evaluation-tag.service';

import { EvaluationTagComponent } from './evaluation-tag.component';

describe('EvaluationTag Management Component', () => {
  let comp: EvaluationTagComponent;
  let fixture: ComponentFixture<EvaluationTagComponent>;
  let service: EvaluationTagService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'evaluation-tag', component: EvaluationTagComponent }]), HttpClientTestingModule],
      declarations: [EvaluationTagComponent],
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
      .overrideTemplate(EvaluationTagComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(EvaluationTagComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(EvaluationTagService);

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
    expect(comp.evaluationTags?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to evaluationTagService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getEvaluationTagIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getEvaluationTagIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
