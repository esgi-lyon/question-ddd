import { IStatisticSubjectTag, NewStatisticSubjectTag } from './statistic-subject-tag.model';

export const sampleWithRequiredData: IStatisticSubjectTag = {
  id: 81153,
};

export const sampleWithPartialData: IStatisticSubjectTag = {
  id: 36413,
  tagId: 13060,
};

export const sampleWithFullData: IStatisticSubjectTag = {
  id: 65206,
  tagId: 58300,
};

export const sampleWithNewData: NewStatisticSubjectTag = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
