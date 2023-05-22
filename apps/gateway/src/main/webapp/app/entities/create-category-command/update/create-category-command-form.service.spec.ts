import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../create-category-command.test-samples';

import { CreateCategoryCommandFormService } from './create-category-command-form.service';

describe('CreateCategoryCommand Form Service', () => {
  let service: CreateCategoryCommandFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateCategoryCommandFormService);
  });

  describe('Service methods', () => {
    describe('createCreateCategoryCommandFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCreateCategoryCommandFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            category: expect.any(Object),
          })
        );
      });

      it('passing ICreateCategoryCommand should create a new form with FormGroup', () => {
        const formGroup = service.createCreateCategoryCommandFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            category: expect.any(Object),
          })
        );
      });
    });

    describe('getCreateCategoryCommand', () => {
      it('should return NewCreateCategoryCommand for default CreateCategoryCommand initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCreateCategoryCommandFormGroup(sampleWithNewData);

        const createCategoryCommand = service.getCreateCategoryCommand(formGroup) as any;

        expect(createCategoryCommand).toMatchObject(sampleWithNewData);
      });

      it('should return NewCreateCategoryCommand for empty CreateCategoryCommand initial value', () => {
        const formGroup = service.createCreateCategoryCommandFormGroup();

        const createCategoryCommand = service.getCreateCategoryCommand(formGroup) as any;

        expect(createCategoryCommand).toMatchObject({});
      });

      it('should return ICreateCategoryCommand', () => {
        const formGroup = service.createCreateCategoryCommandFormGroup(sampleWithRequiredData);

        const createCategoryCommand = service.getCreateCategoryCommand(formGroup) as any;

        expect(createCategoryCommand).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICreateCategoryCommand should not enable id FormControl', () => {
        const formGroup = service.createCreateCategoryCommandFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCreateCategoryCommand should disable id FormControl', () => {
        const formGroup = service.createCreateCategoryCommandFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
