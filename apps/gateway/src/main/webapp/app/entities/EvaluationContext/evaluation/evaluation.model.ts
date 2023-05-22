import { IEvaluationTag } from 'app/entities/evaluation-tag/evaluation-tag.model';
import { IEvaluationQuestion } from 'app/entities/evaluation-question/evaluation-question.model';
import { IAnsweringUser } from 'app/entities/answering-user/answering-user.model';
import { Status } from 'app/entities/enumerations/status.model';
import { DifficultyLevel } from 'app/entities/enumerations/difficulty-level.model';

export interface IEvaluation {
  id: number;
  score?: number | null;
  status?: Status | null;
  answeredQuestionDifficultyLevel?: DifficultyLevel | null;
  tag?: Pick<IEvaluationTag, 'id'> | null;
  question?: Pick<IEvaluationQuestion, 'id'> | null;
  user?: Pick<IAnsweringUser, 'id'> | null;
}

export type NewEvaluation = Omit<IEvaluation, 'id'> & { id: null };
