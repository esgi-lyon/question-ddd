import { IAnswer } from 'app/entities/AnswerContext/answer/answer.model';

export interface IAnswerSubmitCommand {
  id: number;
  answer?: Pick<IAnswer, 'id'> | null;
}

export type NewAnswerSubmitCommand = Omit<IAnswerSubmitCommand, 'id'> & { id: null };
