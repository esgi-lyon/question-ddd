import { ICategory, NewCategory } from './category.model';

export const sampleWithRequiredData: ICategory = {
  id: 2529,
};

export const sampleWithPartialData: ICategory = {
  id: 14860,
  name: 'emulation',
  description: 'up USB Down-sized',
  createdBy: 91149,
};

export const sampleWithFullData: ICategory = {
  id: 97365,
  name: 'transparent task-force Garden',
  description: 'Borders bus',
  createdBy: 72183,
};

export const sampleWithNewData: NewCategory = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
