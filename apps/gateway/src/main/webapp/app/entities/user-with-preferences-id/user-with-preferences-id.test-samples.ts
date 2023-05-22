import { IUserWithPreferencesId, NewUserWithPreferencesId } from './user-with-preferences-id.model';

export const sampleWithRequiredData: IUserWithPreferencesId = {
  id: 55915,
};

export const sampleWithPartialData: IUserWithPreferencesId = {
  id: 16098,
  userId: 62281,
};

export const sampleWithFullData: IUserWithPreferencesId = {
  id: 36665,
  userId: 83126,
};

export const sampleWithNewData: NewUserWithPreferencesId = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
