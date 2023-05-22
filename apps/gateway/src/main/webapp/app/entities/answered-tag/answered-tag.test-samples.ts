import { IAnsweredTag, NewAnsweredTag } from './answered-tag.model';

export const sampleWithRequiredData: IAnsweredTag = {
  id: 19796,
};

export const sampleWithPartialData: IAnsweredTag = {
  id: 70017,
  tagId: 19583,
  name: 'Granite',
};

export const sampleWithFullData: IAnsweredTag = {
  id: 67570,
  tagId: 12638,
  name: 'Sports platforms',
};

export const sampleWithNewData: NewAnsweredTag = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
