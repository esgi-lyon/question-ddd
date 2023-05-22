import { IQuestionSentQuestionResourceTagId } from 'app/entities/question-sent-question-resource-tag-id/question-sent-question-resource-tag-id.model';

export interface ICreateQuestionCommand {
  id: number;
  resource?: Pick<IQuestionSentQuestionResourceTagId, 'id'> | null;
}

export type NewCreateQuestionCommand = Omit<ICreateQuestionCommand, 'id'> & { id: null };
