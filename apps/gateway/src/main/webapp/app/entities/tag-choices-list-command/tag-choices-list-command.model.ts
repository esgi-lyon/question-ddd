export interface ITagChoicesListCommand {
  id: number;
}

export type NewTagChoicesListCommand = Omit<ITagChoicesListCommand, 'id'> & { id: null };
