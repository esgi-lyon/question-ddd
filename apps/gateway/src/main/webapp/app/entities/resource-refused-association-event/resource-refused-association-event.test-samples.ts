import { IResourceRefusedAssociationEvent, NewResourceRefusedAssociationEvent } from './resource-refused-association-event.model';

export const sampleWithRequiredData: IResourceRefusedAssociationEvent = {
  id: 5349,
};

export const sampleWithPartialData: IResourceRefusedAssociationEvent = {
  id: 87694,
};

export const sampleWithFullData: IResourceRefusedAssociationEvent = {
  id: 48875,
};

export const sampleWithNewData: NewResourceRefusedAssociationEvent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
