export interface ITagChoicesListedEvent {
  id: number;
}

export type NewTagChoicesListedEvent = Omit<ITagChoicesListedEvent, 'id'> & { id: null };
