import { IStatisticSubjectTag } from 'app/entities/statistic-subject-tag/statistic-subject-tag.model';

export interface ITagStatsViewedEvent {
  id: number;
  tag?: Pick<IStatisticSubjectTag, 'id'> | null;
}

export type NewTagStatsViewedEvent = Omit<ITagStatsViewedEvent, 'id'> & { id: null };
