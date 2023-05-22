import { IQuestionSentTagInfos, NewQuestionSentTagInfos } from './question-sent-tag-infos.model';

export const sampleWithRequiredData: IQuestionSentTagInfos = {
  id: 51981,
};

export const sampleWithPartialData: IQuestionSentTagInfos = {
  id: 26423,
  name: 'Applications Loan',
};

export const sampleWithFullData: IQuestionSentTagInfos = {
  id: 37455,
  tagId: 26060,
  name: 'Account Tennessee Hawaii',
};

export const sampleWithNewData: NewQuestionSentTagInfos = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
