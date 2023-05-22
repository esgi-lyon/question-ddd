import { IRejectResourceTagCommand, NewRejectResourceTagCommand } from './reject-resource-tag-command.model';

export const sampleWithRequiredData: IRejectResourceTagCommand = {
  id: 71681,
};

export const sampleWithPartialData: IRejectResourceTagCommand = {
  id: 79245,
};

export const sampleWithFullData: IRejectResourceTagCommand = {
  id: 51391,
};

export const sampleWithNewData: NewRejectResourceTagCommand = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
