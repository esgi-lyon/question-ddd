import { ICreateCategoryCommand, NewCreateCategoryCommand } from './create-category-command.model';

export const sampleWithRequiredData: ICreateCategoryCommand = {
  id: 78654,
};

export const sampleWithPartialData: ICreateCategoryCommand = {
  id: 6272,
};

export const sampleWithFullData: ICreateCategoryCommand = {
  id: 67951,
};

export const sampleWithNewData: NewCreateCategoryCommand = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
