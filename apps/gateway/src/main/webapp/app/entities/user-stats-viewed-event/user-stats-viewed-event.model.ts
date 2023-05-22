import { IStatisticSubjectUser } from 'app/entities/statistic-subject-user/statistic-subject-user.model';

export interface IUserStatsViewedEvent {
  id: number;
  user?: Pick<IStatisticSubjectUser, 'id'> | null;
}

export type NewUserStatsViewedEvent = Omit<IUserStatsViewedEvent, 'id'> & { id: null };
