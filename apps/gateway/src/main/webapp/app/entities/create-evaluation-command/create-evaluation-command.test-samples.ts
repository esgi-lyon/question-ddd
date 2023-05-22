import { ICreateEvaluationCommand, NewCreateEvaluationCommand } from './create-evaluation-command.model';

export const sampleWithRequiredData: ICreateEvaluationCommand = {
  id: 78224,
};

export const sampleWithPartialData: ICreateEvaluationCommand = {
  id: 55152,
};

export const sampleWithFullData: ICreateEvaluationCommand = {
  id: 77610,
};

export const sampleWithNewData: NewCreateEvaluationCommand = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
