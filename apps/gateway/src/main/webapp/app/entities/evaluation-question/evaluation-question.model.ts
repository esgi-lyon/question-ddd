export interface IEvaluationQuestion {
  id: number;
  questionId?: number | null;
}

export type NewEvaluationQuestion = Omit<IEvaluationQuestion, 'id'> & { id: null };
