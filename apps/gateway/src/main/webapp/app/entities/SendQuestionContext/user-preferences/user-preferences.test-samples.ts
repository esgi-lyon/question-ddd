import { IUserPreferences, NewUserPreferences } from './user-preferences.model';

export const sampleWithRequiredData: IUserPreferences = {
  id: 15863,
};

export const sampleWithPartialData: IUserPreferences = {
  id: 93150,
};

export const sampleWithFullData: IUserPreferences = {
  id: 98142,
};

export const sampleWithNewData: NewUserPreferences = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
