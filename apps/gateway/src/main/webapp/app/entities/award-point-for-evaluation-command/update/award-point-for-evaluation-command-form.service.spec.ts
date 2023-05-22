import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../award-point-for-evaluation-command.test-samples';

import { AwardPointForEvaluationCommandFormService } from './award-point-for-evaluation-command-form.service';

describe('AwardPointForEvaluationCommand Form Service', () => {
  let service: AwardPointForEvaluationCommandFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AwardPointForEvaluationCommandFormService);
  });

  describe('Service methods', () => {
    describe('createAwardPointForEvaluationCommandFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAwardPointForEvaluationCommandFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            evaluation: expect.any(Object),
          })
        );
      });

      it('passing IAwardPointForEvaluationCommand should create a new form with FormGroup', () => {
        const formGroup = service.createAwardPointForEvaluationCommandFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            evaluation: expect.any(Object),
          })
        );
      });
    });

    describe('getAwardPointForEvaluationCommand', () => {
      it('should return NewAwardPointForEvaluationCommand for default AwardPointForEvaluationCommand initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createAwardPointForEvaluationCommandFormGroup(sampleWithNewData);

        const awardPointForEvaluationCommand = service.getAwardPointForEvaluationCommand(formGroup) as any;

        expect(awardPointForEvaluationCommand).toMatchObject(sampleWithNewData);
      });

      it('should return NewAwardPointForEvaluationCommand for empty AwardPointForEvaluationCommand initial value', () => {
        const formGroup = service.createAwardPointForEvaluationCommandFormGroup();

        const awardPointForEvaluationCommand = service.getAwardPointForEvaluationCommand(formGroup) as any;

        expect(awardPointForEvaluationCommand).toMatchObject({});
      });

      it('should return IAwardPointForEvaluationCommand', () => {
        const formGroup = service.createAwardPointForEvaluationCommandFormGroup(sampleWithRequiredData);

        const awardPointForEvaluationCommand = service.getAwardPointForEvaluationCommand(formGroup) as any;

        expect(awardPointForEvaluationCommand).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAwardPointForEvaluationCommand should not enable id FormControl', () => {
        const formGroup = service.createAwardPointForEvaluationCommandFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAwardPointForEvaluationCommand should disable id FormControl', () => {
        const formGroup = service.createAwardPointForEvaluationCommandFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
