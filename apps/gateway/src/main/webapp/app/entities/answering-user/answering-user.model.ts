export interface IAnsweringUser {
  id: number;
  userId?: number | null;
  name?: string | null;
}

export type NewAnsweringUser = Omit<IAnsweringUser, 'id'> & { id: null };
