import { IQuestionSent } from 'app/entities/SendQuestionContext/question-sent/question-sent.model';

export interface INotifiedQuestionEvent {
  id: number;
  questionResource?: Pick<IQuestionSent, 'id'> | null;
}

export type NewNotifiedQuestionEvent = Omit<INotifiedQuestionEvent, 'id'> & { id: null };
