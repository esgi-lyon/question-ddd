import { IQuestionId, NewQuestionId } from './question-id.model';

export const sampleWithRequiredData: IQuestionId = {
  id: 27114,
};

export const sampleWithPartialData: IQuestionId = {
  id: 70434,
};

export const sampleWithFullData: IQuestionId = {
  id: 69339,
  questionId: 82979,
};

export const sampleWithNewData: NewQuestionId = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
