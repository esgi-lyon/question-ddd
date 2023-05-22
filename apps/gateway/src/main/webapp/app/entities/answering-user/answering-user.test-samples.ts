import { IAnsweringUser, NewAnsweringUser } from './answering-user.model';

export const sampleWithRequiredData: IAnsweringUser = {
  id: 65264,
};

export const sampleWithPartialData: IAnsweringUser = {
  id: 14121,
  name: 'Dong Investor mission-critical',
};

export const sampleWithFullData: IAnsweringUser = {
  id: 69407,
  userId: 75909,
  name: 'Bedfordshire sensor Awesome',
};

export const sampleWithNewData: NewAnsweringUser = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
