import { IQuestionResourceTagInfos, NewQuestionResourceTagInfos } from './question-resource-tag-infos.model';

export const sampleWithRequiredData: IQuestionResourceTagInfos = {
  id: 30527,
};

export const sampleWithPartialData: IQuestionResourceTagInfos = {
  id: 63278,
  tagId: 48321,
};

export const sampleWithFullData: IQuestionResourceTagInfos = {
  id: 51816,
  tagId: 92685,
  name: 'methodologies',
};

export const sampleWithNewData: NewQuestionResourceTagInfos = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
