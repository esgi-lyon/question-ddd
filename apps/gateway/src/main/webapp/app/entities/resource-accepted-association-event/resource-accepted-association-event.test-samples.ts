import { IResourceAcceptedAssociationEvent, NewResourceAcceptedAssociationEvent } from './resource-accepted-association-event.model';

export const sampleWithRequiredData: IResourceAcceptedAssociationEvent = {
  id: 43510,
};

export const sampleWithPartialData: IResourceAcceptedAssociationEvent = {
  id: 95926,
};

export const sampleWithFullData: IResourceAcceptedAssociationEvent = {
  id: 48264,
};

export const sampleWithNewData: NewResourceAcceptedAssociationEvent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
