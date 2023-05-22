import { IEvaluationCreatedEvent, NewEvaluationCreatedEvent } from './evaluation-created-event.model';

export const sampleWithRequiredData: IEvaluationCreatedEvent = {
  id: 80812,
};

export const sampleWithPartialData: IEvaluationCreatedEvent = {
  id: 64629,
};

export const sampleWithFullData: IEvaluationCreatedEvent = {
  id: 45127,
};

export const sampleWithNewData: NewEvaluationCreatedEvent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
