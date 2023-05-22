import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../answer-submit-command.test-samples';

import { AnswerSubmitCommandFormService } from './answer-submit-command-form.service';

describe('AnswerSubmitCommand Form Service', () => {
  let service: AnswerSubmitCommandFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnswerSubmitCommandFormService);
  });

  describe('Service methods', () => {
    describe('createAnswerSubmitCommandFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAnswerSubmitCommandFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            answer: expect.any(Object),
          })
        );
      });

      it('passing IAnswerSubmitCommand should create a new form with FormGroup', () => {
        const formGroup = service.createAnswerSubmitCommandFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            answer: expect.any(Object),
          })
        );
      });
    });

    describe('getAnswerSubmitCommand', () => {
      it('should return NewAnswerSubmitCommand for default AnswerSubmitCommand initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createAnswerSubmitCommandFormGroup(sampleWithNewData);

        const answerSubmitCommand = service.getAnswerSubmitCommand(formGroup) as any;

        expect(answerSubmitCommand).toMatchObject(sampleWithNewData);
      });

      it('should return NewAnswerSubmitCommand for empty AnswerSubmitCommand initial value', () => {
        const formGroup = service.createAnswerSubmitCommandFormGroup();

        const answerSubmitCommand = service.getAnswerSubmitCommand(formGroup) as any;

        expect(answerSubmitCommand).toMatchObject({});
      });

      it('should return IAnswerSubmitCommand', () => {
        const formGroup = service.createAnswerSubmitCommandFormGroup(sampleWithRequiredData);

        const answerSubmitCommand = service.getAnswerSubmitCommand(formGroup) as any;

        expect(answerSubmitCommand).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAnswerSubmitCommand should not enable id FormControl', () => {
        const formGroup = service.createAnswerSubmitCommandFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAnswerSubmitCommand should disable id FormControl', () => {
        const formGroup = service.createAnswerSubmitCommandFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
