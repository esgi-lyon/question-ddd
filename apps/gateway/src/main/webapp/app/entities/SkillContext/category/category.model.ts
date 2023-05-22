export interface ICategory {
  id: number;
  name?: string | null;
  description?: string | null;
  createdBy?: number | null;
}

export type NewCategory = Omit<ICategory, 'id'> & { id: null };
