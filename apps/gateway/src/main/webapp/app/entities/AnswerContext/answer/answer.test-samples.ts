import { IAnswer, NewAnswer } from './answer.model';

export const sampleWithRequiredData: IAnswer = {
  id: 52963,
};

export const sampleWithPartialData: IAnswer = {
  id: 44545,
};

export const sampleWithFullData: IAnswer = {
  id: 40964,
  user: 1501,
};

export const sampleWithNewData: NewAnswer = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
