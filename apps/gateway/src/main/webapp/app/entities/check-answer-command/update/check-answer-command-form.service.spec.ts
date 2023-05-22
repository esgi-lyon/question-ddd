import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../check-answer-command.test-samples';

import { CheckAnswerCommandFormService } from './check-answer-command-form.service';

describe('CheckAnswerCommand Form Service', () => {
  let service: CheckAnswerCommandFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CheckAnswerCommandFormService);
  });

  describe('Service methods', () => {
    describe('createCheckAnswerCommandFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCheckAnswerCommandFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            answer: expect.any(Object),
          })
        );
      });

      it('passing ICheckAnswerCommand should create a new form with FormGroup', () => {
        const formGroup = service.createCheckAnswerCommandFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            answer: expect.any(Object),
          })
        );
      });
    });

    describe('getCheckAnswerCommand', () => {
      it('should return NewCheckAnswerCommand for default CheckAnswerCommand initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCheckAnswerCommandFormGroup(sampleWithNewData);

        const checkAnswerCommand = service.getCheckAnswerCommand(formGroup) as any;

        expect(checkAnswerCommand).toMatchObject(sampleWithNewData);
      });

      it('should return NewCheckAnswerCommand for empty CheckAnswerCommand initial value', () => {
        const formGroup = service.createCheckAnswerCommandFormGroup();

        const checkAnswerCommand = service.getCheckAnswerCommand(formGroup) as any;

        expect(checkAnswerCommand).toMatchObject({});
      });

      it('should return ICheckAnswerCommand', () => {
        const formGroup = service.createCheckAnswerCommandFormGroup(sampleWithRequiredData);

        const checkAnswerCommand = service.getCheckAnswerCommand(formGroup) as any;

        expect(checkAnswerCommand).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICheckAnswerCommand should not enable id FormControl', () => {
        const formGroup = service.createCheckAnswerCommandFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCheckAnswerCommand should disable id FormControl', () => {
        const formGroup = service.createCheckAnswerCommandFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
