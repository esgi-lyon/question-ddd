import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { CreateQuestionCommandService } from '../service/create-question-command.service';

import { CreateQuestionCommandComponent } from './create-question-command.component';

describe('CreateQuestionCommand Management Component', () => {
  let comp: CreateQuestionCommandComponent;
  let fixture: ComponentFixture<CreateQuestionCommandComponent>;
  let service: CreateQuestionCommandService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'create-question-command', component: CreateQuestionCommandComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [CreateQuestionCommandComponent],
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
      .overrideTemplate(CreateQuestionCommandComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CreateQuestionCommandComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(CreateQuestionCommandService);

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
    expect(comp.createQuestionCommands?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to createQuestionCommandService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getCreateQuestionCommandIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getCreateQuestionCommandIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
