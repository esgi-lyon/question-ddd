import { ICategoryCreatedEvent, NewCategoryCreatedEvent } from './category-created-event.model';

export const sampleWithRequiredData: ICategoryCreatedEvent = {
  id: 28411,
};

export const sampleWithPartialData: ICategoryCreatedEvent = {
  id: 42752,
};

export const sampleWithFullData: ICategoryCreatedEvent = {
  id: 35019,
};

export const sampleWithNewData: NewCategoryCreatedEvent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
