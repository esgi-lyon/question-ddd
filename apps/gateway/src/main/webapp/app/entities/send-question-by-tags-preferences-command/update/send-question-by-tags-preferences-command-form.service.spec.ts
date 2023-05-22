import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../send-question-by-tags-preferences-command.test-samples';

import { SendQuestionByTagsPreferencesCommandFormService } from './send-question-by-tags-preferences-command-form.service';

describe('SendQuestionByTagsPreferencesCommand Form Service', () => {
  let service: SendQuestionByTagsPreferencesCommandFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SendQuestionByTagsPreferencesCommandFormService);
  });

  describe('Service methods', () => {
    describe('createSendQuestionByTagsPreferencesCommandFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSendQuestionByTagsPreferencesCommandFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            questionSent: expect.any(Object),
          })
        );
      });

      it('passing ISendQuestionByTagsPreferencesCommand should create a new form with FormGroup', () => {
        const formGroup = service.createSendQuestionByTagsPreferencesCommandFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            questionSent: expect.any(Object),
          })
        );
      });
    });

    describe('getSendQuestionByTagsPreferencesCommand', () => {
      it('should return NewSendQuestionByTagsPreferencesCommand for default SendQuestionByTagsPreferencesCommand initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createSendQuestionByTagsPreferencesCommandFormGroup(sampleWithNewData);

        const sendQuestionByTagsPreferencesCommand = service.getSendQuestionByTagsPreferencesCommand(formGroup) as any;

        expect(sendQuestionByTagsPreferencesCommand).toMatchObject(sampleWithNewData);
      });

      it('should return NewSendQuestionByTagsPreferencesCommand for empty SendQuestionByTagsPreferencesCommand initial value', () => {
        const formGroup = service.createSendQuestionByTagsPreferencesCommandFormGroup();

        const sendQuestionByTagsPreferencesCommand = service.getSendQuestionByTagsPreferencesCommand(formGroup) as any;

        expect(sendQuestionByTagsPreferencesCommand).toMatchObject({});
      });

      it('should return ISendQuestionByTagsPreferencesCommand', () => {
        const formGroup = service.createSendQuestionByTagsPreferencesCommandFormGroup(sampleWithRequiredData);

        const sendQuestionByTagsPreferencesCommand = service.getSendQuestionByTagsPreferencesCommand(formGroup) as any;

        expect(sendQuestionByTagsPreferencesCommand).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISendQuestionByTagsPreferencesCommand should not enable id FormControl', () => {
        const formGroup = service.createSendQuestionByTagsPreferencesCommandFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSendQuestionByTagsPreferencesCommand should disable id FormControl', () => {
        const formGroup = service.createSendQuestionByTagsPreferencesCommandFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
