import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../evaluation.test-samples';

import { EvaluationFormService } from './evaluation-form.service';

describe('Evaluation Form Service', () => {
  let service: EvaluationFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EvaluationFormService);
  });

  describe('Service methods', () => {
    describe('createEvaluationFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createEvaluationFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            score: expect.any(Object),
            status: expect.any(Object),
            answeredQuestionDifficultyLevel: expect.any(Object),
            tag: expect.any(Object),
            question: expect.any(Object),
            user: expect.any(Object),
          })
        );
      });

      it('passing IEvaluation should create a new form with FormGroup', () => {
        const formGroup = service.createEvaluationFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            score: expect.any(Object),
            status: expect.any(Object),
            answeredQuestionDifficultyLevel: expect.any(Object),
            tag: expect.any(Object),
            question: expect.any(Object),
            user: expect.any(Object),
          })
        );
      });
    });

    describe('getEvaluation', () => {
      it('should return NewEvaluation for default Evaluation initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createEvaluationFormGroup(sampleWithNewData);

        const evaluation = service.getEvaluation(formGroup) as any;

        expect(evaluation).toMatchObject(sampleWithNewData);
      });

      it('should return NewEvaluation for empty Evaluation initial value', () => {
        const formGroup = service.createEvaluationFormGroup();

        const evaluation = service.getEvaluation(formGroup) as any;

        expect(evaluation).toMatchObject({});
      });

      it('should return IEvaluation', () => {
        const formGroup = service.createEvaluationFormGroup(sampleWithRequiredData);

        const evaluation = service.getEvaluation(formGroup) as any;

        expect(evaluation).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IEvaluation should not enable id FormControl', () => {
        const formGroup = service.createEvaluationFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewEvaluation should disable id FormControl', () => {
        const formGroup = service.createEvaluationFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
