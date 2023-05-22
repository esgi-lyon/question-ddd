import { ICreateQuestionCommand, NewCreateQuestionCommand } from './create-question-command.model';

export const sampleWithRequiredData: ICreateQuestionCommand = {
  id: 64873,
};

export const sampleWithPartialData: ICreateQuestionCommand = {
  id: 57744,
};

export const sampleWithFullData: ICreateQuestionCommand = {
  id: 14098,
};

export const sampleWithNewData: NewCreateQuestionCommand = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
