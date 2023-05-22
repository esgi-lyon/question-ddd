import { IEvaluatedAnswer } from 'app/entities/evaluated-answer/evaluated-answer.model';

export interface ICheckAnswerCommand {
  id: number;
  answer?: Pick<IEvaluatedAnswer, 'id'> | null;
}

export type NewCheckAnswerCommand = Omit<ICheckAnswerCommand, 'id'> & { id: null };
