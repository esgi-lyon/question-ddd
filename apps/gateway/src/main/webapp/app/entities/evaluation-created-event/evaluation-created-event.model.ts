import { IEvaluation } from 'app/entities/EvaluationContext/evaluation/evaluation.model';

export interface IEvaluationCreatedEvent {
  id: number;
  evaluation?: Pick<IEvaluation, 'id'> | null;
}

export type NewEvaluationCreatedEvent = Omit<IEvaluationCreatedEvent, 'id'> & { id: null };
