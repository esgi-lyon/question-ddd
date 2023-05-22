import { IQuestionResource } from 'app/entities/QuestionContext/question-resource/question-resource.model';

export interface IValidateResourceTagLinkageCommand {
  id: number;
  questionId?: Pick<IQuestionResource, 'id'> | null;
}

export type NewValidateResourceTagLinkageCommand = Omit<IValidateResourceTagLinkageCommand, 'id'> & { id: null };
