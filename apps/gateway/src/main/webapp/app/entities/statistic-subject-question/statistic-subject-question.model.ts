export interface IStatisticSubjectQuestion {
  id: number;
  questionId?: number | null;
}

export type NewStatisticSubjectQuestion = Omit<IStatisticSubjectQuestion, 'id'> & { id: null };
