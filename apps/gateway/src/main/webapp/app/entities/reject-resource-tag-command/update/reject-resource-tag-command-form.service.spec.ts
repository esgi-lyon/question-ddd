import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../reject-resource-tag-command.test-samples';

import { RejectResourceTagCommandFormService } from './reject-resource-tag-command-form.service';

describe('RejectResourceTagCommand Form Service', () => {
  let service: RejectResourceTagCommandFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RejectResourceTagCommandFormService);
  });

  describe('Service methods', () => {
    describe('createRejectResourceTagCommandFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createRejectResourceTagCommandFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            questionId: expect.any(Object),
          })
        );
      });

      it('passing IRejectResourceTagCommand should create a new form with FormGroup', () => {
        const formGroup = service.createRejectResourceTagCommandFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            questionId: expect.any(Object),
          })
        );
      });
    });

    describe('getRejectResourceTagCommand', () => {
      it('should return NewRejectResourceTagCommand for default RejectResourceTagCommand initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createRejectResourceTagCommandFormGroup(sampleWithNewData);

        const rejectResourceTagCommand = service.getRejectResourceTagCommand(formGroup) as any;

        expect(rejectResourceTagCommand).toMatchObject(sampleWithNewData);
      });

      it('should return NewRejectResourceTagCommand for empty RejectResourceTagCommand initial value', () => {
        const formGroup = service.createRejectResourceTagCommandFormGroup();

        const rejectResourceTagCommand = service.getRejectResourceTagCommand(formGroup) as any;

        expect(rejectResourceTagCommand).toMatchObject({});
      });

      it('should return IRejectResourceTagCommand', () => {
        const formGroup = service.createRejectResourceTagCommandFormGroup(sampleWithRequiredData);

        const rejectResourceTagCommand = service.getRejectResourceTagCommand(formGroup) as any;

        expect(rejectResourceTagCommand).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IRejectResourceTagCommand should not enable id FormControl', () => {
        const formGroup = service.createRejectResourceTagCommandFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewRejectResourceTagCommand should disable id FormControl', () => {
        const formGroup = service.createRejectResourceTagCommandFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
