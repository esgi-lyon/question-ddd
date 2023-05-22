import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../create-evaluation-command.test-samples';

import { CreateEvaluationCommandFormService } from './create-evaluation-command-form.service';

describe('CreateEvaluationCommand Form Service', () => {
  let service: CreateEvaluationCommandFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateEvaluationCommandFormService);
  });

  describe('Service methods', () => {
    describe('createCreateEvaluationCommandFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCreateEvaluationCommandFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            answer: expect.any(Object),
          })
        );
      });

      it('passing ICreateEvaluationCommand should create a new form with FormGroup', () => {
        const formGroup = service.createCreateEvaluationCommandFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            answer: expect.any(Object),
          })
        );
      });
    });

    describe('getCreateEvaluationCommand', () => {
      it('should return NewCreateEvaluationCommand for default CreateEvaluationCommand initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCreateEvaluationCommandFormGroup(sampleWithNewData);

        const createEvaluationCommand = service.getCreateEvaluationCommand(formGroup) as any;

        expect(createEvaluationCommand).toMatchObject(sampleWithNewData);
      });

      it('should return NewCreateEvaluationCommand for empty CreateEvaluationCommand initial value', () => {
        const formGroup = service.createCreateEvaluationCommandFormGroup();

        const createEvaluationCommand = service.getCreateEvaluationCommand(formGroup) as any;

        expect(createEvaluationCommand).toMatchObject({});
      });

      it('should return ICreateEvaluationCommand', () => {
        const formGroup = service.createCreateEvaluationCommandFormGroup(sampleWithRequiredData);

        const createEvaluationCommand = service.getCreateEvaluationCommand(formGroup) as any;

        expect(createEvaluationCommand).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICreateEvaluationCommand should not enable id FormControl', () => {
        const formGroup = service.createCreateEvaluationCommandFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCreateEvaluationCommand should disable id FormControl', () => {
        const formGroup = service.createCreateEvaluationCommandFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
