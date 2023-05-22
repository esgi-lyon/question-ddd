import { IEvaluatedAnswer } from 'app/entities/evaluated-answer/evaluated-answer.model';

export interface IAwardedPointEvent {
  id: number;
  answer?: Pick<IEvaluatedAnswer, 'id'> | null;
}

export type NewAwardedPointEvent = Omit<IAwardedPointEvent, 'id'> & { id: null };
