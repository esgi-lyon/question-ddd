import { ILeaderBoard } from 'app/entities/StatContext/leader-board/leader-board.model';

export interface IEvaluationId {
  id: number;
  evaluationId?: number | null;
  leaderBoard?: Pick<ILeaderBoard, 'id'> | null;
}

export type NewEvaluationId = Omit<IEvaluationId, 'id'> & { id: null };
