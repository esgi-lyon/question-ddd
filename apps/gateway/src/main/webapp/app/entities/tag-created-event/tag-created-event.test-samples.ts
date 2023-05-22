import { ITagCreatedEvent, NewTagCreatedEvent } from './tag-created-event.model';

export const sampleWithRequiredData: ITagCreatedEvent = {
  id: 94212,
};

export const sampleWithPartialData: ITagCreatedEvent = {
  id: 34699,
};

export const sampleWithFullData: ITagCreatedEvent = {
  id: 57048,
};

export const sampleWithNewData: NewTagCreatedEvent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
