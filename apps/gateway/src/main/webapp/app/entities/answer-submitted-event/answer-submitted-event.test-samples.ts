import { IAnswerSubmittedEvent, NewAnswerSubmittedEvent } from './answer-submitted-event.model';

export const sampleWithRequiredData: IAnswerSubmittedEvent = {
  id: 1975,
};

export const sampleWithPartialData: IAnswerSubmittedEvent = {
  id: 1002,
};

export const sampleWithFullData: IAnswerSubmittedEvent = {
  id: 82734,
};

export const sampleWithNewData: NewAnswerSubmittedEvent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
