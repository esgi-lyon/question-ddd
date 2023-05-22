import { IAwardPointForEvaluationCommand, NewAwardPointForEvaluationCommand } from './award-point-for-evaluation-command.model';

export const sampleWithRequiredData: IAwardPointForEvaluationCommand = {
  id: 58624,
};

export const sampleWithPartialData: IAwardPointForEvaluationCommand = {
  id: 74513,
};

export const sampleWithFullData: IAwardPointForEvaluationCommand = {
  id: 2784,
};

export const sampleWithNewData: NewAwardPointForEvaluationCommand = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
