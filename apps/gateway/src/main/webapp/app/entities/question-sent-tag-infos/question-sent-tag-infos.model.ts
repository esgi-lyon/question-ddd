import { IQuestion } from 'app/entities/question/question.model';

export interface IQuestionSentTagInfos {
  id: number;
  tagId?: number | null;
  name?: string | null;
  question?: Pick<IQuestion, 'id'> | null;
}

export type NewQuestionSentTagInfos = Omit<IQuestionSentTagInfos, 'id'> & { id: null };
