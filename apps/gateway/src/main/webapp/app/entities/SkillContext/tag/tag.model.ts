import { ICategory } from 'app/entities/SkillContext/category/category.model';

export interface ITag {
  id: number;
  name?: string | null;
  createdBy?: number | null;
  category?: Pick<ICategory, 'id'> | null;
}

export type NewTag = Omit<ITag, 'id'> & { id: null };
