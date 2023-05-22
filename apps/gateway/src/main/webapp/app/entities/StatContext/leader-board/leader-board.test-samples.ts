import { DifficultyLevel } from 'app/entities/enumerations/difficulty-level.model';

import { ILeaderBoard, NewLeaderBoard } from './leader-board.model';

export const sampleWithRequiredData: ILeaderBoard = {
  id: 28494,
};

export const sampleWithPartialData: ILeaderBoard = {
  id: 64670,
};

export const sampleWithFullData: ILeaderBoard = {
  id: 43164,
  difficultyLevel: DifficultyLevel['MEDIUM'],
};

export const sampleWithNewData: NewLeaderBoard = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
