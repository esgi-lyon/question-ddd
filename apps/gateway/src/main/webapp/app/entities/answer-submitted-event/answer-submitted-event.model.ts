import { IAnswer } from 'app/entities/AnswerContext/answer/answer.model';

export interface IAnswerSubmittedEvent {
  id: number;
  answer?: Pick<IAnswer, 'id'> | null;
}

export type NewAnswerSubmittedEvent = Omit<IAnswerSubmittedEvent, 'id'> & { id: null };
