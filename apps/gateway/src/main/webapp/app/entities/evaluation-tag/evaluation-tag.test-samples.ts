import { IEvaluationTag, NewEvaluationTag } from './evaluation-tag.model';

export const sampleWithRequiredData: IEvaluationTag = {
  id: 9483,
};

export const sampleWithPartialData: IEvaluationTag = {
  id: 56742,
  tagId: 92588,
};

export const sampleWithFullData: IEvaluationTag = {
  id: 78959,
  tagId: 90237,
  name: 'repurpose',
};

export const sampleWithNewData: NewEvaluationTag = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
