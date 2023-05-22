export interface IStatisticSubjectTag {
  id: number;
  tagId?: number | null;
}

export type NewStatisticSubjectTag = Omit<IStatisticSubjectTag, 'id'> & { id: null };
