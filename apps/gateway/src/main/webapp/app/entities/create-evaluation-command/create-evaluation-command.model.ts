import { IEvaluatedAnswer } from 'app/entities/evaluated-answer/evaluated-answer.model';

export interface ICreateEvaluationCommand {
  id: number;
  answer?: Pick<IEvaluatedAnswer, 'id'> | null;
}

export type NewCreateEvaluationCommand = Omit<ICreateEvaluationCommand, 'id'> & { id: null };
