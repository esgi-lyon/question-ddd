import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../question-sent.test-samples';

import { QuestionSentFormService } from './question-sent-form.service';

describe('QuestionSent Form Service', () => {
  let service: QuestionSentFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QuestionSentFormService);
  });

  describe('Service methods', () => {
    describe('createQuestionSentFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createQuestionSentFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            resource: expect.any(Object),
            sentDate: expect.any(Object),
            viewedDate: expect.any(Object),
            answeredDate: expect.any(Object),
            status: expect.any(Object),
          })
        );
      });

      it('passing IQuestionSent should create a new form with FormGroup', () => {
        const formGroup = service.createQuestionSentFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            resource: expect.any(Object),
            sentDate: expect.any(Object),
            viewedDate: expect.any(Object),
            answeredDate: expect.any(Object),
            status: expect.any(Object),
          })
        );
      });
    });

    describe('getQuestionSent', () => {
      it('should return NewQuestionSent for default QuestionSent initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createQuestionSentFormGroup(sampleWithNewData);

        const questionSent = service.getQuestionSent(formGroup) as any;

        expect(questionSent).toMatchObject(sampleWithNewData);
      });

      it('should return NewQuestionSent for empty QuestionSent initial value', () => {
        const formGroup = service.createQuestionSentFormGroup();

        const questionSent = service.getQuestionSent(formGroup) as any;

        expect(questionSent).toMatchObject({});
      });

      it('should return IQuestionSent', () => {
        const formGroup = service.createQuestionSentFormGroup(sampleWithRequiredData);

        const questionSent = service.getQuestionSent(formGroup) as any;

        expect(questionSent).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IQuestionSent should not enable id FormControl', () => {
        const formGroup = service.createQuestionSentFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewQuestionSent should disable id FormControl', () => {
        const formGroup = service.createQuestionSentFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
