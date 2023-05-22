import { IEvaluationQuestion, NewEvaluationQuestion } from './evaluation-question.model';

export const sampleWithRequiredData: IEvaluationQuestion = {
  id: 14753,
};

export const sampleWithPartialData: IEvaluationQuestion = {
  id: 45041,
  questionId: 86928,
};

export const sampleWithFullData: IEvaluationQuestion = {
  id: 18225,
  questionId: 32264,
};

export const sampleWithNewData: NewEvaluationQuestion = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
