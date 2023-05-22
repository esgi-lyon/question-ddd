import { IEvaluatedAnswer } from 'app/entities/evaluated-answer/evaluated-answer.model';

export interface IAnswerCheckedEvent {
  id: number;
  answer?: Pick<IEvaluatedAnswer, 'id'> | null;
}

export type NewAnswerCheckedEvent = Omit<IAnswerCheckedEvent, 'id'> & { id: null };
