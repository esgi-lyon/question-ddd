import { IStatisticSubjectQuestion } from 'app/entities/statistic-subject-question/statistic-subject-question.model';

export interface IQuestionStatsViewedEvent {
  id: number;
  question?: Pick<IStatisticSubjectQuestion, 'id'> | null;
}

export type NewQuestionStatsViewedEvent = Omit<IQuestionStatsViewedEvent, 'id'> & { id: null };
