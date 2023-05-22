export interface IQuestionResourceTagInfos {
  id: number;
  tagId?: number | null;
  name?: string | null;
}

export type NewQuestionResourceTagInfos = Omit<IQuestionResourceTagInfos, 'id'> & { id: null };
