import { ICreateResourceCommand, NewCreateResourceCommand } from './create-resource-command.model';

export const sampleWithRequiredData: ICreateResourceCommand = {
  id: 18956,
};

export const sampleWithPartialData: ICreateResourceCommand = {
  id: 22555,
};

export const sampleWithFullData: ICreateResourceCommand = {
  id: 35276,
};

export const sampleWithNewData: NewCreateResourceCommand = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
