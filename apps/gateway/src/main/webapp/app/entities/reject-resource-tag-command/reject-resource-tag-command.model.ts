import { IQuestionResource } from 'app/entities/QuestionContext/question-resource/question-resource.model';

export interface IRejectResourceTagCommand {
  id: number;
  questionId?: Pick<IQuestionResource, 'id'> | null;
}

export type NewRejectResourceTagCommand = Omit<IRejectResourceTagCommand, 'id'> & { id: null };
