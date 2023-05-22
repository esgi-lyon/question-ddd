import { IUserPreferences } from 'app/entities/SendQuestionContext/user-preferences/user-preferences.model';

export interface IUserPreferencesTagInfos {
  id: number;
  tagId?: number | null;
  name?: string | null;
  userPreferences?: Pick<IUserPreferences, 'id'> | null;
}

export type NewUserPreferencesTagInfos = Omit<IUserPreferencesTagInfos, 'id'> & { id: null };
