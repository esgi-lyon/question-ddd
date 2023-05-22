export interface ITagInfos {
  id: number;
  tagId?: number | null;
  name?: string | null;
}

export type NewTagInfos = Omit<ITagInfos, 'id'> & { id: null };
