import { IQuestionStatsViewedEvent, NewQuestionStatsViewedEvent } from './question-stats-viewed-event.model';

export const sampleWithRequiredData: IQuestionStatsViewedEvent = {
  id: 44382,
};

export const sampleWithPartialData: IQuestionStatsViewedEvent = {
  id: 69481,
};

export const sampleWithFullData: IQuestionStatsViewedEvent = {
  id: 29982,
};

export const sampleWithNewData: NewQuestionStatsViewedEvent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
