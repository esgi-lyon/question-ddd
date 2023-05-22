import dayjs from 'dayjs/esm';
import { QuestionNotificationStatus } from 'app/entities/enumerations/question-notification-status.model';

export interface IQuestionSent {
  id: number;
  resource?: number | null;
  sentDate?: dayjs.Dayjs | null;
  viewedDate?: dayjs.Dayjs | null;
  answeredDate?: dayjs.Dayjs | null;
  status?: QuestionNotificationStatus | null;
}

export type NewQuestionSent = Omit<IQuestionSent, 'id'> & { id: null };
