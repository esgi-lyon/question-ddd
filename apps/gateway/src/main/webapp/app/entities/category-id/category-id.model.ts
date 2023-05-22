export interface ICategoryId {
  id: number;
  categoryId?: number | null;
}

export type NewCategoryId = Omit<ICategoryId, 'id'> & { id: null };
