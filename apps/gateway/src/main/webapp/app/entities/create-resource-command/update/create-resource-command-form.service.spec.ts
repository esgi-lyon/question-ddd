import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../create-resource-command.test-samples';

import { CreateResourceCommandFormService } from './create-resource-command-form.service';

describe('CreateResourceCommand Form Service', () => {
  let service: CreateResourceCommandFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateResourceCommandFormService);
  });

  describe('Service methods', () => {
    describe('createCreateResourceCommandFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCreateResourceCommandFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            questionId: expect.any(Object),
          })
        );
      });

      it('passing ICreateResourceCommand should create a new form with FormGroup', () => {
        const formGroup = service.createCreateResourceCommandFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            questionId: expect.any(Object),
          })
        );
      });
    });

    describe('getCreateResourceCommand', () => {
      it('should return NewCreateResourceCommand for default CreateResourceCommand initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCreateResourceCommandFormGroup(sampleWithNewData);

        const createResourceCommand = service.getCreateResourceCommand(formGroup) as any;

        expect(createResourceCommand).toMatchObject(sampleWithNewData);
      });

      it('should return NewCreateResourceCommand for empty CreateResourceCommand initial value', () => {
        const formGroup = service.createCreateResourceCommandFormGroup();

        const createResourceCommand = service.getCreateResourceCommand(formGroup) as any;

        expect(createResourceCommand).toMatchObject({});
      });

      it('should return ICreateResourceCommand', () => {
        const formGroup = service.createCreateResourceCommandFormGroup(sampleWithRequiredData);

        const createResourceCommand = service.getCreateResourceCommand(formGroup) as any;

        expect(createResourceCommand).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICreateResourceCommand should not enable id FormControl', () => {
        const formGroup = service.createCreateResourceCommandFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCreateResourceCommand should disable id FormControl', () => {
        const formGroup = service.createCreateResourceCommandFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
