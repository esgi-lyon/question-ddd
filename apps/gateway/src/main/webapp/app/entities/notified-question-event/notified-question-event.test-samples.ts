import { INotifiedQuestionEvent, NewNotifiedQuestionEvent } from './notified-question-event.model';

export const sampleWithRequiredData: INotifiedQuestionEvent = {
  id: 50093,
};

export const sampleWithPartialData: INotifiedQuestionEvent = {
  id: 55797,
};

export const sampleWithFullData: INotifiedQuestionEvent = {
  id: 48487,
};

export const sampleWithNewData: NewNotifiedQuestionEvent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
