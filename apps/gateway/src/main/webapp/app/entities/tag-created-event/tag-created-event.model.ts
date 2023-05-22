import { ITagInfos } from 'app/entities/tag-infos/tag-infos.model';

export interface ITagCreatedEvent {
  id: number;
  tagId?: Pick<ITagInfos, 'id'> | null;
}

export type NewTagCreatedEvent = Omit<ITagCreatedEvent, 'id'> & { id: null };
