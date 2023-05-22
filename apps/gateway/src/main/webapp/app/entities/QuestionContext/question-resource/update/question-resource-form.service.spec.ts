import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../question-resource.test-samples';

import { QuestionResourceFormService } from './question-resource-form.service';

describe('QuestionResource Form Service', () => {
  let service: QuestionResourceFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QuestionResourceFormService);
  });

  describe('Service methods', () => {
    describe('createQuestionResourceFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createQuestionResourceFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            questionContent: expect.any(Object),
            tag: expect.any(Object),
            questionState: expect.any(Object),
            resourceType: expect.any(Object),
          })
        );
      });

      it('passing IQuestionResource should create a new form with FormGroup', () => {
        const formGroup = service.createQuestionResourceFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            questionContent: expect.any(Object),
            tag: expect.any(Object),
            questionState: expect.any(Object),
            resourceType: expect.any(Object),
          })
        );
      });
    });

    describe('getQuestionResource', () => {
      it('should return NewQuestionResource for default QuestionResource initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createQuestionResourceFormGroup(sampleWithNewData);

        const questionResource = service.getQuestionResource(formGroup) as any;

        expect(questionResource).toMatchObject(sampleWithNewData);
      });

      it('should return NewQuestionResource for empty QuestionResource initial value', () => {
        const formGroup = service.createQuestionResourceFormGroup();

        const questionResource = service.getQuestionResource(formGroup) as any;

        expect(questionResource).toMatchObject({});
      });

      it('should return IQuestionResource', () => {
        const formGroup = service.createQuestionResourceFormGroup(sampleWithRequiredData);

        const questionResource = service.getQuestionResource(formGroup) as any;

        expect(questionResource).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IQuestionResource should not enable id FormControl', () => {
        const formGroup = service.createQuestionResourceFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewQuestionResource should disable id FormControl', () => {
        const formGroup = service.createQuestionResourceFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
