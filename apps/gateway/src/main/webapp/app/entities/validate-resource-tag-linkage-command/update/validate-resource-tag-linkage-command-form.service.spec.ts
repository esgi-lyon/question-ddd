import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../validate-resource-tag-linkage-command.test-samples';

import { ValidateResourceTagLinkageCommandFormService } from './validate-resource-tag-linkage-command-form.service';

describe('ValidateResourceTagLinkageCommand Form Service', () => {
  let service: ValidateResourceTagLinkageCommandFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ValidateResourceTagLinkageCommandFormService);
  });

  describe('Service methods', () => {
    describe('createValidateResourceTagLinkageCommandFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createValidateResourceTagLinkageCommandFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            questionId: expect.any(Object),
          })
        );
      });

      it('passing IValidateResourceTagLinkageCommand should create a new form with FormGroup', () => {
        const formGroup = service.createValidateResourceTagLinkageCommandFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            questionId: expect.any(Object),
          })
        );
      });
    });

    describe('getValidateResourceTagLinkageCommand', () => {
      it('should return NewValidateResourceTagLinkageCommand for default ValidateResourceTagLinkageCommand initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createValidateResourceTagLinkageCommandFormGroup(sampleWithNewData);

        const validateResourceTagLinkageCommand = service.getValidateResourceTagLinkageCommand(formGroup) as any;

        expect(validateResourceTagLinkageCommand).toMatchObject(sampleWithNewData);
      });

      it('should return NewValidateResourceTagLinkageCommand for empty ValidateResourceTagLinkageCommand initial value', () => {
        const formGroup = service.createValidateResourceTagLinkageCommandFormGroup();

        const validateResourceTagLinkageCommand = service.getValidateResourceTagLinkageCommand(formGroup) as any;

        expect(validateResourceTagLinkageCommand).toMatchObject({});
      });

      it('should return IValidateResourceTagLinkageCommand', () => {
        const formGroup = service.createValidateResourceTagLinkageCommandFormGroup(sampleWithRequiredData);

        const validateResourceTagLinkageCommand = service.getValidateResourceTagLinkageCommand(formGroup) as any;

        expect(validateResourceTagLinkageCommand).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IValidateResourceTagLinkageCommand should not enable id FormControl', () => {
        const formGroup = service.createValidateResourceTagLinkageCommandFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewValidateResourceTagLinkageCommand should disable id FormControl', () => {
        const formGroup = service.createValidateResourceTagLinkageCommandFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
