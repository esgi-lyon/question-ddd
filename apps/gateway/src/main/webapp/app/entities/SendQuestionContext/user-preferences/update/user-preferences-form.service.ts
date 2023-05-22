import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IUserPreferences, NewUserPreferences } from '../user-preferences.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IUserPreferences for edit and NewUserPreferencesFormGroupInput for create.
 */
type UserPreferencesFormGroupInput = IUserPreferences | PartialWithRequiredKeyOf<NewUserPreferences>;

type UserPreferencesFormDefaults = Pick<NewUserPreferences, 'id'>;

type UserPreferencesFormGroupContent = {
  id: FormControl<IUserPreferences['id'] | NewUserPreferences['id']>;
  user: FormControl<IUserPreferences['user']>;
};

export type UserPreferencesFormGroup = FormGroup<UserPreferencesFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class UserPreferencesFormService {
  createUserPreferencesFormGroup(userPreferences: UserPreferencesFormGroupInput = { id: null }): UserPreferencesFormGroup {
    const userPreferencesRawValue = {
      ...this.getFormDefaults(),
      ...userPreferences,
    };
    return new FormGroup<UserPreferencesFormGroupContent>({
      id: new FormControl(
        { value: userPreferencesRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      user: new FormControl(userPreferencesRawValue.user),
    });
  }

  getUserPreferences(form: UserPreferencesFormGroup): IUserPreferences | NewUserPreferences {
    return form.getRawValue() as IUserPreferences | NewUserPreferences;
  }

  resetForm(form: UserPreferencesFormGroup, userPreferences: UserPreferencesFormGroupInput): void {
    const userPreferencesRawValue = { ...this.getFormDefaults(), ...userPreferences };
    form.reset(
      {
        ...userPreferencesRawValue,
        id: { value: userPreferencesRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): UserPreferencesFormDefaults {
    return {
      id: null,
    };
  }
}
