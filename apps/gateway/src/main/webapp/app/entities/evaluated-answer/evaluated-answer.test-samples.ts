import { IEvaluatedAnswer, NewEvaluatedAnswer } from './evaluated-answer.model';

export const sampleWithRequiredData: IEvaluatedAnswer = {
  id: 5821,
};

export const sampleWithPartialData: IEvaluatedAnswer = {
  id: 76422,
};

export const sampleWithFullData: IEvaluatedAnswer = {
  id: 8993,
  answerId: 32343,
};

export const sampleWithNewData: NewEvaluatedAnswer = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
