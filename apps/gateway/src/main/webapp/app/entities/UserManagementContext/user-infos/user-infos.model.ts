import { Roles } from 'app/entities/enumerations/roles.model';
import { UserStatus } from 'app/entities/enumerations/user-status.model';

export interface IUserInfos {
  id: number;
  firstname?: string | null;
  lastname?: string | null;
  role?: Roles | null;
  status?: UserStatus | null;
}

export type NewUserInfos = Omit<IUserInfos, 'id'> & { id: null };
