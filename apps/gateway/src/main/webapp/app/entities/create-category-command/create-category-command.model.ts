import { ICategory } from 'app/entities/SkillContext/category/category.model';

export interface ICreateCategoryCommand {
  id: number;
  category?: Pick<ICategory, 'id'> | null;
}

export type NewCreateCategoryCommand = Omit<ICreateCategoryCommand, 'id'> & { id: null };
