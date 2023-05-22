import { IQuestionSent } from 'app/entities/SendQuestionContext/question-sent/question-sent.model';

export interface ICreatedQuestionEvent {
  id: number;
  questionAndTag?: Pick<IQuestionSent, 'id'> | null;
}

export type NewCreatedQuestionEvent = Omit<ICreatedQuestionEvent, 'id'> & { id: null };
