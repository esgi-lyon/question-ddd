import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AwardPointForEvaluationCommandFormService } from './award-point-for-evaluation-command-form.service';
import { AwardPointForEvaluationCommandService } from '../service/award-point-for-evaluation-command.service';
import { IAwardPointForEvaluationCommand } from '../award-point-for-evaluation-command.model';
import { IEvaluation } from 'app/entities/EvaluationContext/evaluation/evaluation.model';
import { EvaluationService } from 'app/entities/EvaluationContext/evaluation/service/evaluation.service';

import { AwardPointForEvaluationCommandUpdateComponent } from './award-point-for-evaluation-command-update.component';

describe('AwardPointForEvaluationCommand Management Update Component', () => {
  let comp: AwardPointForEvaluationCommandUpdateComponent;
  let fixture: ComponentFixture<AwardPointForEvaluationCommandUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let awardPointForEvaluationCommandFormService: AwardPointForEvaluationCommandFormService;
  let awardPointForEvaluationCommandService: AwardPointForEvaluationCommandService;
  let evaluationService: EvaluationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [AwardPointForEvaluationCommandUpdateComponent],
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
      .overrideTemplate(AwardPointForEvaluationCommandUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AwardPointForEvaluationCommandUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    awardPointForEvaluationCommandFormService = TestBed.inject(AwardPointForEvaluationCommandFormService);
    awardPointForEvaluationCommandService = TestBed.inject(AwardPointForEvaluationCommandService);
    evaluationService = TestBed.inject(EvaluationService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Evaluation query and add missing value', () => {
      const awardPointForEvaluationCommand: IAwardPointForEvaluationCommand = { id: 456 };
      const evaluation: IEvaluation = { id: 72471 };
      awardPointForEvaluationCommand.evaluation = evaluation;

      const evaluationCollection: IEvaluation[] = [{ id: 96478 }];
      jest.spyOn(evaluationService, 'query').mockReturnValue(of(new HttpResponse({ body: evaluationCollection })));
      const additionalEvaluations = [evaluation];
      const expectedCollection: IEvaluation[] = [...additionalEvaluations, ...evaluationCollection];
      jest.spyOn(evaluationService, 'addEvaluationToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ awardPointForEvaluationCommand });
      comp.ngOnInit();

      expect(evaluationService.query).toHaveBeenCalled();
      expect(evaluationService.addEvaluationToCollectionIfMissing).toHaveBeenCalledWith(
        evaluationCollection,
        ...additionalEvaluations.map(expect.objectContaining)
      );
      expect(comp.evaluationsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const awardPointForEvaluationCommand: IAwardPointForEvaluationCommand = { id: 456 };
      const evaluation: IEvaluation = { id: 24621 };
      awardPointForEvaluationCommand.evaluation = evaluation;

      activatedRoute.data = of({ awardPointForEvaluationCommand });
      comp.ngOnInit();

      expect(comp.evaluationsSharedCollection).toContain(evaluation);
      expect(comp.awardPointForEvaluationCommand).toEqual(awardPointForEvaluationCommand);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAwardPointForEvaluationCommand>>();
      const awardPointForEvaluationCommand = { id: 123 };
      jest
        .spyOn(awardPointForEvaluationCommandFormService, 'getAwardPointForEvaluationCommand')
        .mockReturnValue(awardPointForEvaluationCommand);
      jest.spyOn(awardPointForEvaluationCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ awardPointForEvaluationCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: awardPointForEvaluationCommand }));
      saveSubject.complete();

      // THEN
      expect(awardPointForEvaluationCommandFormService.getAwardPointForEvaluationCommand).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(awardPointForEvaluationCommandService.update).toHaveBeenCalledWith(expect.objectContaining(awardPointForEvaluationCommand));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAwardPointForEvaluationCommand>>();
      const awardPointForEvaluationCommand = { id: 123 };
      jest.spyOn(awardPointForEvaluationCommandFormService, 'getAwardPointForEvaluationCommand').mockReturnValue({ id: null });
      jest.spyOn(awardPointForEvaluationCommandService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ awardPointForEvaluationCommand: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: awardPointForEvaluationCommand }));
      saveSubject.complete();

      // THEN
      expect(awardPointForEvaluationCommandFormService.getAwardPointForEvaluationCommand).toHaveBeenCalled();
      expect(awardPointForEvaluationCommandService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAwardPointForEvaluationCommand>>();
      const awardPointForEvaluationCommand = { id: 123 };
      jest.spyOn(awardPointForEvaluationCommandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ awardPointForEvaluationCommand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(awardPointForEvaluationCommandService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareEvaluation', () => {
      it('Should forward to evaluationService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(evaluationService, 'compareEvaluation');
        comp.compareEvaluation(entity, entity2);
        expect(evaluationService.compareEvaluation).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
