import { IEvaluation } from 'app/entities/EvaluationContext/evaluation/evaluation.model';

export interface IAwardPointForEvaluationCommand {
  id: number;
  evaluation?: Pick<IEvaluation, 'id'> | null;
}

export type NewAwardPointForEvaluationCommand = Omit<IAwardPointForEvaluationCommand, 'id'> & { id: null };
