import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IUserInfos, NewUserInfos } from '../user-infos.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IUserInfos for edit and NewUserInfosFormGroupInput for create.
 */
type UserInfosFormGroupInput = IUserInfos | PartialWithRequiredKeyOf<NewUserInfos>;

type UserInfosFormDefaults = Pick<NewUserInfos, 'id'>;

type UserInfosFormGroupContent = {
  id: FormControl<IUserInfos['id'] | NewUserInfos['id']>;
  firstname: FormControl<IUserInfos['firstname']>;
  lastname: FormControl<IUserInfos['lastname']>;
  role: FormControl<IUserInfos['role']>;
  status: FormControl<IUserInfos['status']>;
};

export type UserInfosFormGroup = FormGroup<UserInfosFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class UserInfosFormService {
  createUserInfosFormGroup(userInfos: UserInfosFormGroupInput = { id: null }): UserInfosFormGroup {
    const userInfosRawValue = {
      ...this.getFormDefaults(),
      ...userInfos,
    };
    return new FormGroup<UserInfosFormGroupContent>({
      id: new FormControl(
        { value: userInfosRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      firstname: new FormControl(userInfosRawValue.firstname),
      lastname: new FormControl(userInfosRawValue.lastname),
      role: new FormControl(userInfosRawValue.role),
      status: new FormControl(userInfosRawValue.status),
    });
  }

  getUserInfos(form: UserInfosFormGroup): IUserInfos | NewUserInfos {
    return form.getRawValue() as IUserInfos | NewUserInfos;
  }

  resetForm(form: UserInfosFormGroup, userInfos: UserInfosFormGroupInput): void {
    const userInfosRawValue = { ...this.getFormDefaults(), ...userInfos };
    form.reset(
      {
        ...userInfosRawValue,
        id: { value: userInfosRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): UserInfosFormDefaults {
    return {
      id: null,
    };
  }
}
