import dayjs from 'dayjs/esm';

import { QuestionNotificationStatus } from 'app/entities/enumerations/question-notification-status.model';

import { IQuestionSent, NewQuestionSent } from './question-sent.model';

export const sampleWithRequiredData: IQuestionSent = {
  id: 64890,
};

export const sampleWithPartialData: IQuestionSent = {
  id: 56096,
  sentDate: dayjs('2023-04-22'),
  status: QuestionNotificationStatus['VIEWED'],
};

export const sampleWithFullData: IQuestionSent = {
  id: 86049,
  resource: 44295,
  sentDate: dayjs('2023-04-22'),
  viewedDate: dayjs('2023-04-22'),
  answeredDate: dayjs('2023-04-22'),
  status: QuestionNotificationStatus['SENT'],
};

export const sampleWithNewData: NewQuestionSent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
