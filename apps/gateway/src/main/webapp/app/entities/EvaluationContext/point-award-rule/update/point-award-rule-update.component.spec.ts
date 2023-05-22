import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { PointAwardRuleFormService } from './point-award-rule-form.service';
import { PointAwardRuleService } from '../service/point-award-rule.service';
import { IPointAwardRule } from '../point-award-rule.model';

import { PointAwardRuleUpdateComponent } from './point-award-rule-update.component';

describe('PointAwardRule Management Update Component', () => {
  let comp: PointAwardRuleUpdateComponent;
  let fixture: ComponentFixture<PointAwardRuleUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let pointAwardRuleFormService: PointAwardRuleFormService;
  let pointAwardRuleService: PointAwardRuleService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [PointAwardRuleUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(PointAwardRuleUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PointAwardRuleUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    pointAwardRuleFormService = TestBed.inject(PointAwardRuleFormService);
    pointAwardRuleService = TestBed.inject(PointAwardRuleService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const pointAwardRule: IPointAwardRule = { id: 456 };

      activatedRoute.data = of({ pointAwardRule });
      comp.ngOnInit();

      expect(comp.pointAwardRule).toEqual(pointAwardRule);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPointAwardRule>>();
      const pointAwardRule = { id: 123 };
      jest.spyOn(pointAwardRuleFormService, 'getPointAwardRule').mockReturnValue(pointAwardRule);
      jest.spyOn(pointAwardRuleService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ pointAwardRule });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: pointAwardRule }));
      saveSubject.complete();

      // THEN
      expect(pointAwardRuleFormService.getPointAwardRule).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(pointAwardRuleService.update).toHaveBeenCalledWith(expect.objectContaining(pointAwardRule));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPointAwardRule>>();
      const pointAwardRule = { id: 123 };
      jest.spyOn(pointAwardRuleFormService, 'getPointAwardRule').mockReturnValue({ id: null });
      jest.spyOn(pointAwardRuleService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ pointAwardRule: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: pointAwardRule }));
      saveSubject.complete();

      // THEN
      expect(pointAwardRuleFormService.getPointAwardRule).toHaveBeenCalled();
      expect(pointAwardRuleService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPointAwardRule>>();
      const pointAwardRule = { id: 123 };
      jest.spyOn(pointAwardRuleService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ pointAwardRule });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(pointAwardRuleService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
