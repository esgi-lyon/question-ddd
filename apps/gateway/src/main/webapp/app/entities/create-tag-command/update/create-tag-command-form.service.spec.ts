import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../create-tag-command.test-samples';

import { CreateTagCommandFormService } from './create-tag-command-form.service';

describe('CreateTagCommand Form Service', () => {
  let service: CreateTagCommandFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateTagCommandFormService);
  });

  describe('Service methods', () => {
    describe('createCreateTagCommandFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCreateTagCommandFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tag: expect.any(Object),
          })
        );
      });

      it('passing ICreateTagCommand should create a new form with FormGroup', () => {
        const formGroup = service.createCreateTagCommandFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tag: expect.any(Object),
          })
        );
      });
    });

    describe('getCreateTagCommand', () => {
      it('should return NewCreateTagCommand for default CreateTagCommand initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCreateTagCommandFormGroup(sampleWithNewData);

        const createTagCommand = service.getCreateTagCommand(formGroup) as any;

        expect(createTagCommand).toMatchObject(sampleWithNewData);
      });

      it('should return NewCreateTagCommand for empty CreateTagCommand initial value', () => {
        const formGroup = service.createCreateTagCommandFormGroup();

        const createTagCommand = service.getCreateTagCommand(formGroup) as any;

        expect(createTagCommand).toMatchObject({});
      });

      it('should return ICreateTagCommand', () => {
        const formGroup = service.createCreateTagCommandFormGroup(sampleWithRequiredData);

        const createTagCommand = service.getCreateTagCommand(formGroup) as any;

        expect(createTagCommand).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICreateTagCommand should not enable id FormControl', () => {
        const formGroup = service.createCreateTagCommandFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCreateTagCommand should disable id FormControl', () => {
        const formGroup = service.createCreateTagCommandFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
