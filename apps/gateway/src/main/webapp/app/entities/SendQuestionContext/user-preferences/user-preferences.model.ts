import { IUserWithPreferencesId } from 'app/entities/user-with-preferences-id/user-with-preferences-id.model';

export interface IUserPreferences {
  id: number;
  user?: Pick<IUserWithPreferencesId, 'id'> | null;
}

export type NewUserPreferences = Omit<IUserPreferences, 'id'> & { id: null };
