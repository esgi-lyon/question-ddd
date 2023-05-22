import { ITagInfos, NewTagInfos } from './tag-infos.model';

export const sampleWithRequiredData: ITagInfos = {
  id: 17651,
};

export const sampleWithPartialData: ITagInfos = {
  id: 9983,
};

export const sampleWithFullData: ITagInfos = {
  id: 27857,
  tagId: 55037,
  name: 'multi-tasking',
};

export const sampleWithNewData: NewTagInfos = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
