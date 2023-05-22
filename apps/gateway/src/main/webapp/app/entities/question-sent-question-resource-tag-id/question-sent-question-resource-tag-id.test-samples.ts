import { IQuestionSentQuestionResourceTagId, NewQuestionSentQuestionResourceTagId } from './question-sent-question-resource-tag-id.model';

export const sampleWithRequiredData: IQuestionSentQuestionResourceTagId = {
  id: 9549,
};

export const sampleWithPartialData: IQuestionSentQuestionResourceTagId = {
  id: 70804,
};

export const sampleWithFullData: IQuestionSentQuestionResourceTagId = {
  id: 56110,
  tagId: 56663,
};

export const sampleWithNewData: NewQuestionSentQuestionResourceTagId = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
