import { ITag } from 'app/entities/SkillContext/tag/tag.model';

export interface ICreateTagCommand {
  id: number;
  tag?: Pick<ITag, 'id'> | null;
}

export type NewCreateTagCommand = Omit<ICreateTagCommand, 'id'> & { id: null };
