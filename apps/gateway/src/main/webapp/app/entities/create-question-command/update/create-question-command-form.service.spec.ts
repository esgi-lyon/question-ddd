import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../create-question-command.test-samples';

import { CreateQuestionCommandFormService } from './create-question-command-form.service';

describe('CreateQuestionCommand Form Service', () => {
  let service: CreateQuestionCommandFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateQuestionCommandFormService);
  });

  describe('Service methods', () => {
    describe('createCreateQuestionCommandFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCreateQuestionCommandFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            resource: expect.any(Object),
          })
        );
      });

      it('passing ICreateQuestionCommand should create a new form with FormGroup', () => {
        const formGroup = service.createCreateQuestionCommandFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            resource: expect.any(Object),
          })
        );
      });
    });

    describe('getCreateQuestionCommand', () => {
      it('should return NewCreateQuestionCommand for default CreateQuestionCommand initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCreateQuestionCommandFormGroup(sampleWithNewData);

        const createQuestionCommand = service.getCreateQuestionCommand(formGroup) as any;

        expect(createQuestionCommand).toMatchObject(sampleWithNewData);
      });

      it('should return NewCreateQuestionCommand for empty CreateQuestionCommand initial value', () => {
        const formGroup = service.createCreateQuestionCommandFormGroup();

        const createQuestionCommand = service.getCreateQuestionCommand(formGroup) as any;

        expect(createQuestionCommand).toMatchObject({});
      });

      it('should return ICreateQuestionCommand', () => {
        const formGroup = service.createCreateQuestionCommandFormGroup(sampleWithRequiredData);

        const createQuestionCommand = service.getCreateQuestionCommand(formGroup) as any;

        expect(createQuestionCommand).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICreateQuestionCommand should not enable id FormControl', () => {
        const formGroup = service.createCreateQuestionCommandFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCreateQuestionCommand should disable id FormControl', () => {
        const formGroup = service.createCreateQuestionCommandFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
