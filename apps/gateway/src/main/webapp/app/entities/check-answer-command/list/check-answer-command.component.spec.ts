import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { CheckAnswerCommandService } from '../service/check-answer-command.service';

import { CheckAnswerCommandComponent } from './check-answer-command.component';

describe('CheckAnswerCommand Management Component', () => {
  let comp: CheckAnswerCommandComponent;
  let fixture: ComponentFixture<CheckAnswerCommandComponent>;
  let service: CheckAnswerCommandService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'check-answer-command', component: CheckAnswerCommandComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [CheckAnswerCommandComponent],
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
      .overrideTemplate(CheckAnswerCommandComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CheckAnswerCommandComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(CheckAnswerCommandService);

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
    expect(comp.checkAnswerCommands?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to checkAnswerCommandService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getCheckAnswerCommandIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getCheckAnswerCommandIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
