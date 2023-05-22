import { IEvaluationId, NewEvaluationId } from './evaluation-id.model';

export const sampleWithRequiredData: IEvaluationId = {
  id: 32111,
};

export const sampleWithPartialData: IEvaluationId = {
  id: 21982,
  evaluationId: 98121,
};

export const sampleWithFullData: IEvaluationId = {
  id: 62338,
  evaluationId: 57129,
};

export const sampleWithNewData: NewEvaluationId = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
