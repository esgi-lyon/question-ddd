import { Status } from 'app/entities/enumerations/status.model';
import { DifficultyLevel } from 'app/entities/enumerations/difficulty-level.model';

import { IEvaluation, NewEvaluation } from './evaluation.model';

export const sampleWithRequiredData: IEvaluation = {
  id: 84166,
};

export const sampleWithPartialData: IEvaluation = {
  id: 98026,
  status: Status['VALID'],
  answeredQuestionDifficultyLevel: DifficultyLevel['MEDIUM'],
};

export const sampleWithFullData: IEvaluation = {
  id: 81311,
  score: 16700,
  status: Status['INVALID'],
  answeredQuestionDifficultyLevel: DifficultyLevel['HARD'],
};

export const sampleWithNewData: NewEvaluation = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
