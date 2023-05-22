import { States } from 'app/entities/enumerations/states.model';
import { Types } from 'app/entities/enumerations/types.model';

export interface IQuestionResource {
  id: number;
  questionContent?: string | null;
  tag?: number | null;
  questionState?: States | null;
  resourceType?: Types | null;
}

export type NewQuestionResource = Omit<IQuestionResource, 'id'> & { id: null };
