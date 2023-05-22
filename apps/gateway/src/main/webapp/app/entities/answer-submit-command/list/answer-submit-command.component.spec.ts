import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { AnswerSubmitCommandService } from '../service/answer-submit-command.service';

import { AnswerSubmitCommandComponent } from './answer-submit-command.component';

describe('AnswerSubmitCommand Management Component', () => {
  let comp: AnswerSubmitCommandComponent;
  let fixture: ComponentFixture<AnswerSubmitCommandComponent>;
  let service: AnswerSubmitCommandService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'answer-submit-command', component: AnswerSubmitCommandComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [AnswerSubmitCommandComponent],
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
      .overrideTemplate(AnswerSubmitCommandComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AnswerSubmitCommandComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AnswerSubmitCommandService);

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
    expect(comp.answerSubmitCommands?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to answerSubmitCommandService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getAnswerSubmitCommandIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getAnswerSubmitCommandIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
