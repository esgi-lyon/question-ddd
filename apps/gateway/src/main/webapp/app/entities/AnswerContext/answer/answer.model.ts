import { IQuestionId } from 'app/entities/question-id/question-id.model';
import { IAnsweredTag } from 'app/entities/answered-tag/answered-tag.model';

export interface IAnswer {
  id: number;
  user?: number | null;
  question?: Pick<IQuestionId, 'id'> | null;
  answeredTag?: Pick<IAnsweredTag, 'id'> | null;
}

export type NewAnswer = Omit<IAnswer, 'id'> & { id: null };
