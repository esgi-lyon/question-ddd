import { ICategoryId } from 'app/entities/category-id/category-id.model';

export interface ICategoryCreatedEvent {
  id: number;
  categoryId?: Pick<ICategoryId, 'id'> | null;
}

export type NewCategoryCreatedEvent = Omit<ICategoryCreatedEvent, 'id'> & { id: null };
