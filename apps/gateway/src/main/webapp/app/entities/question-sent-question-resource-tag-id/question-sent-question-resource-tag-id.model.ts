export interface IQuestionSentQuestionResourceTagId {
  id: number;
  tagId?: number | null;
}

export type NewQuestionSentQuestionResourceTagId = Omit<IQuestionSentQuestionResourceTagId, 'id'> & { id: null };
