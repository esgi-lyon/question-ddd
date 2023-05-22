import { DifficultyLevel } from 'app/entities/enumerations/difficulty-level.model';
import { UserLevel } from 'app/entities/enumerations/user-level.model';

import { IPointAwardRule, NewPointAwardRule } from './point-award-rule.model';

export const sampleWithRequiredData: IPointAwardRule = {
  id: 91633,
};

export const sampleWithPartialData: IPointAwardRule = {
  id: 54494,
  difficultyLevel: DifficultyLevel['MEDIUM'],
  userLevel: UserLevel['NEW'],
};

export const sampleWithFullData: IPointAwardRule = {
  id: 58434,
  scoreEvolution: 86008,
  difficultyLevel: DifficultyLevel['HARD'],
  userLevel: UserLevel['NEW'],
};

export const sampleWithNewData: NewPointAwardRule = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
