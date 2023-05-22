import { IQuestionSent } from 'app/entities/SendQuestionContext/question-sent/question-sent.model';

export interface ISendQuestionByTagsPreferencesCommand {
  id: number;
  questionSent?: Pick<IQuestionSent, 'id'> | null;
}

export type NewSendQuestionByTagsPreferencesCommand = Omit<ISendQuestionByTagsPreferencesCommand, 'id'> & { id: null };
