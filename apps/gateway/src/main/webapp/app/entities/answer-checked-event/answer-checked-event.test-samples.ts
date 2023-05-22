import { IAnswerCheckedEvent, NewAnswerCheckedEvent } from './answer-checked-event.model';

export const sampleWithRequiredData: IAnswerCheckedEvent = {
  id: 68576,
};

export const sampleWithPartialData: IAnswerCheckedEvent = {
  id: 99903,
};

export const sampleWithFullData: IAnswerCheckedEvent = {
  id: 62620,
};

export const sampleWithNewData: NewAnswerCheckedEvent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
