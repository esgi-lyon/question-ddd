import { IStatisticSubjectQuestion, NewStatisticSubjectQuestion } from './statistic-subject-question.model';

export const sampleWithRequiredData: IStatisticSubjectQuestion = {
  id: 71053,
};

export const sampleWithPartialData: IStatisticSubjectQuestion = {
  id: 35158,
};

export const sampleWithFullData: IStatisticSubjectQuestion = {
  id: 83688,
  questionId: 33086,
};

export const sampleWithNewData: NewStatisticSubjectQuestion = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
