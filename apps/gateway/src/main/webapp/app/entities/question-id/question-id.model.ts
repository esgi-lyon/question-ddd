export interface IQuestionId {
  id: number;
  questionId?: number | null;
}

export type NewQuestionId = Omit<IQuestionId, 'id'> & { id: null };
