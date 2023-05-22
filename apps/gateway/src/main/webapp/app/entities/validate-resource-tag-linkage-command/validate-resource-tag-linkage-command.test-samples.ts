import { IValidateResourceTagLinkageCommand, NewValidateResourceTagLinkageCommand } from './validate-resource-tag-linkage-command.model';

export const sampleWithRequiredData: IValidateResourceTagLinkageCommand = {
  id: 74245,
};

export const sampleWithPartialData: IValidateResourceTagLinkageCommand = {
  id: 6397,
};

export const sampleWithFullData: IValidateResourceTagLinkageCommand = {
  id: 82662,
};

export const sampleWithNewData: NewValidateResourceTagLinkageCommand = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
