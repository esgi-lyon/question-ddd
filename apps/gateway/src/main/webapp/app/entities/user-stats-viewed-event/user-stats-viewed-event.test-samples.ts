import { IUserStatsViewedEvent, NewUserStatsViewedEvent } from './user-stats-viewed-event.model';

export const sampleWithRequiredData: IUserStatsViewedEvent = {
  id: 37824,
};

export const sampleWithPartialData: IUserStatsViewedEvent = {
  id: 50830,
};

export const sampleWithFullData: IUserStatsViewedEvent = {
  id: 9080,
};

export const sampleWithNewData: NewUserStatsViewedEvent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
