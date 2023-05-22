import { IUserPreferencesTagInfos, NewUserPreferencesTagInfos } from './user-preferences-tag-infos.model';

export const sampleWithRequiredData: IUserPreferencesTagInfos = {
  id: 38505,
};

export const sampleWithPartialData: IUserPreferencesTagInfos = {
  id: 63388,
  name: 'Games Awesome',
};

export const sampleWithFullData: IUserPreferencesTagInfos = {
  id: 1657,
  tagId: 47755,
  name: 'Clothing',
};

export const sampleWithNewData: NewUserPreferencesTagInfos = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
