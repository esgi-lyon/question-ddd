import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { AwardPointForEvaluationCommandService } from '../service/award-point-for-evaluation-command.service';

import { AwardPointForEvaluationCommandComponent } from './award-point-for-evaluation-command.component';

describe('AwardPointForEvaluationCommand Management Component', () => {
  let comp: AwardPointForEvaluationCommandComponent;
  let fixture: ComponentFixture<AwardPointForEvaluationCommandComponent>;
  let service: AwardPointForEvaluationCommandService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([
          { path: 'award-point-for-evaluation-command', component: AwardPointForEvaluationCommandComponent },
        ]),
        HttpClientTestingModule,
      ],
      declarations: [AwardPointForEvaluationCommandComponent],
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
      .overrideTemplate(AwardPointForEvaluationCommandComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AwardPointForEvaluationCommandComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AwardPointForEvaluationCommandService);

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
    expect(comp.awardPointForEvaluationCommands?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to awardPointForEvaluationCommandService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getAwardPointForEvaluationCommandIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getAwardPointForEvaluationCommandIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
