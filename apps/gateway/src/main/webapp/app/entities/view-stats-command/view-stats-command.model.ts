import { IStatisticSubjectUser } from 'app/entities/statistic-subject-user/statistic-subject-user.model';
import { IStatisticSubjectQuestion } from 'app/entities/statistic-subject-question/statistic-subject-question.model';
import { IStatisticSubjectTag } from 'app/entities/statistic-subject-tag/statistic-subject-tag.model';

export interface IViewStatsCommand {
  id: number;
  user?: Pick<IStatisticSubjectUser, 'id'> | null;
  question?: Pick<IStatisticSubjectQuestion, 'id'> | null;
  tag?: Pick<IStatisticSubjectTag, 'id'> | null;
}

export type NewViewStatsCommand = Omit<IViewStatsCommand, 'id'> & { id: null };
