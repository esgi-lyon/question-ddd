import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { PointAwardRuleService } from '../service/point-award-rule.service';

import { PointAwardRuleComponent } from './point-award-rule.component';

describe('PointAwardRule Management Component', () => {
  let comp: PointAwardRuleComponent;
  let fixture: ComponentFixture<PointAwardRuleComponent>;
  let service: PointAwardRuleService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'point-award-rule', component: PointAwardRuleComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [PointAwardRuleComponent],
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
      .overrideTemplate(PointAwardRuleComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PointAwardRuleComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(PointAwardRuleService);

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
    expect(comp.pointAwardRules?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to pointAwardRuleService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getPointAwardRuleIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getPointAwardRuleIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
