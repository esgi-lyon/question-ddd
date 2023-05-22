import { IQuestionSent } from 'app/entities/SendQuestionContext/question-sent/question-sent.model';

export interface IQuestionSentTagId {
  id: number;
  tagId?: number | null;
  questionSent?: Pick<IQuestionSent, 'id'> | null;
}

export type NewQuestionSentTagId = Omit<IQuestionSentTagId, 'id'> & { id: null };
