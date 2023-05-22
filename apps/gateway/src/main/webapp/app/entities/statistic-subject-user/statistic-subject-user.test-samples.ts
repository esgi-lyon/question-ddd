import { IStatisticSubjectUser, NewStatisticSubjectUser } from './statistic-subject-user.model';

export const sampleWithRequiredData: IStatisticSubjectUser = {
  id: 94059,
};

export const sampleWithPartialData: IStatisticSubjectUser = {
  id: 26718,
};

export const sampleWithFullData: IStatisticSubjectUser = {
  id: 57485,
  userId: 57478,
};

export const sampleWithNewData: NewStatisticSubjectUser = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
