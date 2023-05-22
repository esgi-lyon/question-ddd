export interface IEvaluationTag {
  id: number;
  tagId?: number | null;
  name?: string | null;
}

export type NewEvaluationTag = Omit<IEvaluationTag, 'id'> & { id: null };
