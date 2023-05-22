import { ICreateTagCommand, NewCreateTagCommand } from './create-tag-command.model';

export const sampleWithRequiredData: ICreateTagCommand = {
  id: 80096,
};

export const sampleWithPartialData: ICreateTagCommand = {
  id: 70770,
};

export const sampleWithFullData: ICreateTagCommand = {
  id: 38822,
};

export const sampleWithNewData: NewCreateTagCommand = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
