export interface IStatisticSubjectUser {
  id: number;
  userId?: number | null;
}

export type NewStatisticSubjectUser = Omit<IStatisticSubjectUser, 'id'> & { id: null };
