export interface IAnsweredTag {
  id: number;
  tagId?: number | null;
  name?: string | null;
}

export type NewAnsweredTag = Omit<IAnsweredTag, 'id'> & { id: null };
