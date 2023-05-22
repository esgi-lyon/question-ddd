export interface IEvaluatedAnswer {
  id: number;
  answerId?: number | null;
}

export type NewEvaluatedAnswer = Omit<IEvaluatedAnswer, 'id'> & { id: null };
