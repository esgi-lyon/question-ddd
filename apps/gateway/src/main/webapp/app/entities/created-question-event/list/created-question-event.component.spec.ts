import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { CreatedQuestionEventService } from '../service/created-question-event.service';

import { CreatedQuestionEventComponent } from './created-question-event.component';

describe('CreatedQuestionEvent Management Component', () => {
  let comp: CreatedQuestionEventComponent;
  let fixture: ComponentFixture<CreatedQuestionEventComponent>;
  let service: CreatedQuestionEventService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'created-question-event', component: CreatedQuestionEventComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [CreatedQuestionEventComponent],
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
      .overrideTemplate(CreatedQuestionEventComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CreatedQuestionEventComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(CreatedQuestionEventService);

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
    expect(comp.createdQuestionEvents?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to createdQuestionEventService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getCreatedQuestionEventIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getCreatedQuestionEventIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
