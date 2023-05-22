import { IResourceWaitingForAssociationEvent, NewResourceWaitingForAssociationEvent } from './resource-waiting-for-association-event.model';

export const sampleWithRequiredData: IResourceWaitingForAssociationEvent = {
  id: 32779,
};

export const sampleWithPartialData: IResourceWaitingForAssociationEvent = {
  id: 1150,
};

export const sampleWithFullData: IResourceWaitingForAssociationEvent = {
  id: 6925,
};

export const sampleWithNewData: NewResourceWaitingForAssociationEvent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
