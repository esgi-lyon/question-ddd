import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { CreateEvaluationCommandService } from '../service/create-evaluation-command.service';

import { CreateEvaluationCommandComponent } from './create-evaluation-command.component';

describe('CreateEvaluationCommand Management Component', () => {
  let comp: CreateEvaluationCommandComponent;
  let fixture: ComponentFixture<CreateEvaluationCommandComponent>;
  let service: CreateEvaluationCommandService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'create-evaluation-command', component: CreateEvaluationCommandComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [CreateEvaluationCommandComponent],
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
      .overrideTemplate(CreateEvaluationCommandComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CreateEvaluationCommandComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(CreateEvaluationCommandService);

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
    expect(comp.createEvaluationCommands?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to createEvaluationCommandService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getCreateEvaluationCommandIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getCreateEvaluationCommandIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
