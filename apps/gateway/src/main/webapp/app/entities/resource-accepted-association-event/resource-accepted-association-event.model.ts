import { IQuestionResource } from 'app/entities/QuestionContext/question-resource/question-resource.model';
import { IQuestionResourceTagInfos } from 'app/entities/question-resource-tag-infos/question-resource-tag-infos.model';

export interface IResourceAcceptedAssociationEvent {
  id: number;
  questionId?: Pick<IQuestionResource, 'id'> | null;
  tagId?: Pick<IQuestionResourceTagInfos, 'id'> | null;
}

export type NewResourceAcceptedAssociationEvent = Omit<IResourceAcceptedAssociationEvent, 'id'> & { id: null };
