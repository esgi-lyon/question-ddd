import { ITagChoicesListedEvent, NewTagChoicesListedEvent } from './tag-choices-listed-event.model';

export const sampleWithRequiredData: ITagChoicesListedEvent = {
  id: 66311,
};

export const sampleWithPartialData: ITagChoicesListedEvent = {
  id: 75790,
};

export const sampleWithFullData: ITagChoicesListedEvent = {
  id: 57819,
};

export const sampleWithNewData: NewTagChoicesListedEvent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
