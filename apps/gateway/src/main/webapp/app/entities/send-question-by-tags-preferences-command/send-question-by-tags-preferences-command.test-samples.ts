import {
  ISendQuestionByTagsPreferencesCommand,
  NewSendQuestionByTagsPreferencesCommand,
} from './send-question-by-tags-preferences-command.model';

export const sampleWithRequiredData: ISendQuestionByTagsPreferencesCommand = {
  id: 19409,
};

export const sampleWithPartialData: ISendQuestionByTagsPreferencesCommand = {
  id: 50097,
};

export const sampleWithFullData: ISendQuestionByTagsPreferencesCommand = {
  id: 10138,
};

export const sampleWithNewData: NewSendQuestionByTagsPreferencesCommand = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
