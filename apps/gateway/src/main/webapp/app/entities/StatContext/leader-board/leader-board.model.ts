import { IStatisticSubjectTag } from 'app/entities/statistic-subject-tag/statistic-subject-tag.model';
import { DifficultyLevel } from 'app/entities/enumerations/difficulty-level.model';

export interface ILeaderBoard {
  id: number;
  difficultyLevel?: DifficultyLevel | null;
  tagId?: Pick<IStatisticSubjectTag, 'id'> | null;
}

export type NewLeaderBoard = Omit<ILeaderBoard, 'id'> & { id: null };
