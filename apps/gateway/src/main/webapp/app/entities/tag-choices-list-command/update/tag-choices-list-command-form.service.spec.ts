import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../tag-choices-list-command.test-samples';

import { TagChoicesListCommandFormService } from './tag-choices-list-command-form.service';

describe('TagChoicesListCommand Form Service', () => {
  let service: TagChoicesListCommandFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TagChoicesListCommandFormService);
  });

  describe('Service methods', () => {
    describe('createTagChoicesListCommandFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createTagChoicesListCommandFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
          })
        );
      });

      it('passing ITagChoicesListCommand should create a new form with FormGroup', () => {
        const formGroup = service.createTagChoicesListCommandFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
          })
        );
      });
    });

    describe('getTagChoicesListCommand', () => {
      it('should return NewTagChoicesListCommand for default TagChoicesListCommand initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createTagChoicesListCommandFormGroup(sampleWithNewData);

        const tagChoicesListCommand = service.getTagChoicesListCommand(formGroup) as any;

        expect(tagChoicesListCommand).toMatchObject(sampleWithNewData);
      });

      it('should return NewTagChoicesListCommand for empty TagChoicesListCommand initial value', () => {
        const formGroup = service.createTagChoicesListCommandFormGroup();

        const tagChoicesListCommand = service.getTagChoicesListCommand(formGroup) as any;

        expect(tagChoicesListCommand).toMatchObject({});
      });

      it('should return ITagChoicesListCommand', () => {
        const formGroup = service.createTagChoicesListCommandFormGroup(sampleWithRequiredData);

        const tagChoicesListCommand = service.getTagChoicesListCommand(formGroup) as any;

        expect(tagChoicesListCommand).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ITagChoicesListCommand should not enable id FormControl', () => {
        const formGroup = service.createTagChoicesListCommandFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewTagChoicesListCommand should disable id FormControl', () => {
        const formGroup = service.createTagChoicesListCommandFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
