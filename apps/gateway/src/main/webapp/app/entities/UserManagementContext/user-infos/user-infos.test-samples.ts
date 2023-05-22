import { Roles } from 'app/entities/enumerations/roles.model';
import { UserStatus } from 'app/entities/enumerations/user-status.model';

import { IUserInfos, NewUserInfos } from './user-infos.model';

export const sampleWithRequiredData: IUserInfos = {
  id: 74977,
};

export const sampleWithPartialData: IUserInfos = {
  id: 65154,
  lastname: 'benchmark Baby',
  status: UserStatus['WAITING_VALIDATION'],
};

export const sampleWithFullData: IUserInfos = {
  id: 27721,
  firstname: 'withdrawal',
  lastname: 'Account',
  role: Roles['STUDENT'],
  status: UserStatus['WAITING_VALIDATION'],
};

export const sampleWithNewData: NewUserInfos = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
