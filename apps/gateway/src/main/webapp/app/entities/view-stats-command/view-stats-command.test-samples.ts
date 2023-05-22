import { IViewStatsCommand, NewViewStatsCommand } from './view-stats-command.model';

export const sampleWithRequiredData: IViewStatsCommand = {
  id: 86834,
};

export const sampleWithPartialData: IViewStatsCommand = {
  id: 47222,
};

export const sampleWithFullData: IViewStatsCommand = {
  id: 87514,
};

export const sampleWithNewData: NewViewStatsCommand = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
