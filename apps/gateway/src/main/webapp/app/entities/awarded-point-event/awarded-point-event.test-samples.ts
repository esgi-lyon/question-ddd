import { IAwardedPointEvent, NewAwardedPointEvent } from './awarded-point-event.model';

export const sampleWithRequiredData: IAwardedPointEvent = {
  id: 84970,
};

export const sampleWithPartialData: IAwardedPointEvent = {
  id: 64768,
};

export const sampleWithFullData: IAwardedPointEvent = {
  id: 76350,
};

export const sampleWithNewData: NewAwardedPointEvent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
