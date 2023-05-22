import { DifficultyLevel } from 'app/entities/enumerations/difficulty-level.model';
import { UserLevel } from 'app/entities/enumerations/user-level.model';

export interface IPointAwardRule {
  id: number;
  scoreEvolution?: number | null;
  difficultyLevel?: DifficultyLevel | null;
  userLevel?: UserLevel | null;
}

export type NewPointAwardRule = Omit<IPointAwardRule, 'id'> & { id: null };
