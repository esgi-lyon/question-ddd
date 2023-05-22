import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICreateTagCommand, NewCreateTagCommand } from '../create-tag-command.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICreateTagCommand for edit and NewCreateTagCommandFormGroupInput for create.
 */
type CreateTagCommandFormGroupInput = ICreateTagCommand | PartialWithRequiredKeyOf<NewCreateTagCommand>;

type CreateTagCommandFormDefaults = Pick<NewCreateTagCommand, 'id'>;

type CreateTagCommandFormGroupContent = {
  id: FormControl<ICreateTagCommand['id'] | NewCreateTagCommand['id']>;
  tag: FormControl<ICreateTagCommand['tag']>;
};

export type CreateTagCommandFormGroup = FormGroup<CreateTagCommandFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CreateTagCommandFormService {
  createCreateTagCommandFormGroup(createTagCommand: CreateTagCommandFormGroupInput = { id: null }): CreateTagCommandFormGroup {
    const createTagCommandRawValue = {
      ...this.getFormDefaults(),
      ...createTagCommand,
    };
    return new FormGroup<CreateTagCommandFormGroupContent>({
      id: new FormControl(
        { value: createTagCommandRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      tag: new FormControl(createTagCommandRawValue.tag),
    });
  }

  getCreateTagCommand(form: CreateTagCommandFormGroup): ICreateTagCommand | NewCreateTagCommand {
    return form.getRawValue() as ICreateTagCommand | NewCreateTagCommand;
  }

  resetForm(form: CreateTagCommandFormGroup, createTagCommand: CreateTagCommandFormGroupInput): void {
    const createTagCommandRawValue = { ...this.getFormDefaults(), ...createTagCommand };
    form.reset(
      {
        ...createTagCommandRawValue,
        id: { value: createTagCommandRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CreateTagCommandFormDefaults {
    return {
      id: null,
    };
  }
}
