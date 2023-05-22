export interface IUserWithPreferencesId {
  id: number;
  userId?: number | null;
}

export type NewUserWithPreferencesId = Omit<IUserWithPreferencesId, 'id'> & { id: null };
