import { ITagStatsViewedEvent, NewTagStatsViewedEvent } from './tag-stats-viewed-event.model';

export const sampleWithRequiredData: ITagStatsViewedEvent = {
  id: 69134,
};

export const sampleWithPartialData: ITagStatsViewedEvent = {
  id: 79023,
};

export const sampleWithFullData: ITagStatsViewedEvent = {
  id: 11742,
};

export const sampleWithNewData: NewTagStatsViewedEvent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
