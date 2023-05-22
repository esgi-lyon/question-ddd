import { IQuestionSentTagInfos } from 'app/entities/question-sent-tag-infos/question-sent-tag-infos.model';

export interface IQuestion {
  id: number;
  resourceCorrectTag?: Pick<IQuestionSentTagInfos, 'id'> | null;
}

export type NewQuestion = Omit<IQuestion, 'id'> & { id: null };
