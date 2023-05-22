import { IAnswerSubmitCommand, NewAnswerSubmitCommand } from './answer-submit-command.model';

export const sampleWithRequiredData: IAnswerSubmitCommand = {
  id: 11553,
};

export const sampleWithPartialData: IAnswerSubmitCommand = {
  id: 61451,
};

export const sampleWithFullData: IAnswerSubmitCommand = {
  id: 42367,
};

export const sampleWithNewData: NewAnswerSubmitCommand = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
