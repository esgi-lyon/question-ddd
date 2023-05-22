import { IQuestionResource } from 'app/entities/QuestionContext/question-resource/question-resource.model';

export interface ICreateResourceCommand {
  id: number;
  questionId?: Pick<IQuestionResource, 'id'> | null;
}

export type NewCreateResourceCommand = Omit<ICreateResourceCommand, 'id'> & { id: null };
