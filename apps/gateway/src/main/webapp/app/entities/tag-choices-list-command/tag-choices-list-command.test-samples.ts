import { ITagChoicesListCommand, NewTagChoicesListCommand } from './tag-choices-list-command.model';

export const sampleWithRequiredData: ITagChoicesListCommand = {
  id: 25237,
};

export const sampleWithPartialData: ITagChoicesListCommand = {
  id: 36399,
};

export const sampleWithFullData: ITagChoicesListCommand = {
  id: 91574,
};

export const sampleWithNewData: NewTagChoicesListCommand = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
