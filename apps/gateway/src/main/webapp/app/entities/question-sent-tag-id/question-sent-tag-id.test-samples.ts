import { IQuestionSentTagId, NewQuestionSentTagId } from './question-sent-tag-id.model';

export const sampleWithRequiredData: IQuestionSentTagId = {
  id: 68517,
};

export const sampleWithPartialData: IQuestionSentTagId = {
  id: 31410,
  tagId: 65229,
};

export const sampleWithFullData: IQuestionSentTagId = {
  id: 62605,
  tagId: 65174,
};

export const sampleWithNewData: NewQuestionSentTagId = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
