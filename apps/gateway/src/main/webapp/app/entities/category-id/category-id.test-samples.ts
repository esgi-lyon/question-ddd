import { ICategoryId, NewCategoryId } from './category-id.model';

export const sampleWithRequiredData: ICategoryId = {
  id: 65093,
};

export const sampleWithPartialData: ICategoryId = {
  id: 79793,
};

export const sampleWithFullData: ICategoryId = {
  id: 90573,
  categoryId: 43726,
};

export const sampleWithNewData: NewCategoryId = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
