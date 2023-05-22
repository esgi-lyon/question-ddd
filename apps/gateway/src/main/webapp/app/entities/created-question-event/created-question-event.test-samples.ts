import { ICreatedQuestionEvent, NewCreatedQuestionEvent } from './created-question-event.model';

export const sampleWithRequiredData: ICreatedQuestionEvent = {
  id: 5788,
};

export const sampleWithPartialData: ICreatedQuestionEvent = {
  id: 68783,
};

export const sampleWithFullData: ICreatedQuestionEvent = {
  id: 65890,
};

export const sampleWithNewData: NewCreatedQuestionEvent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
