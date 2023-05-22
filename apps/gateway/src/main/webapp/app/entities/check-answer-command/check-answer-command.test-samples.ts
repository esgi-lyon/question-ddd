import { ICheckAnswerCommand, NewCheckAnswerCommand } from './check-answer-command.model';

export const sampleWithRequiredData: ICheckAnswerCommand = {
  id: 34270,
};

export const sampleWithPartialData: ICheckAnswerCommand = {
  id: 4650,
};

export const sampleWithFullData: ICheckAnswerCommand = {
  id: 22818,
};

export const sampleWithNewData: NewCheckAnswerCommand = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
