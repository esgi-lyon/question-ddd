import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICreateResourceCommand, NewCreateResourceCommand } from '../create-resource-command.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICreateResourceCommand for edit and NewCreateResourceCommandFormGroupInput for create.
 */
type CreateResourceCommandFormGroupInput = ICreateResourceCommand | PartialWithRequiredKeyOf<NewCreateResourceCommand>;

type CreateResourceCommandFormDefaults = Pick<NewCreateResourceCommand, 'id'>;

type CreateResourceCommandFormGroupContent = {
  id: FormControl<ICreateResourceCommand['id'] | NewCreateResourceCommand['id']>;
  questionId: FormControl<ICreateResourceCommand['questionId']>;
};

export type CreateResourceCommandFormGroup = FormGroup<CreateResourceCommandFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CreateResourceCommandFormService {
  createCreateResourceCommandFormGroup(
    createResourceCommand: CreateResourceCommandFormGroupInput = { id: null }
  ): CreateResourceCommandFormGroup {
    const createResourceCommandRawValue = {
      ...this.getFormDefaults(),
      ...createResourceCommand,
    };
    return new FormGroup<CreateResourceCommandFormGroupContent>({
      id: new FormControl(
        { value: createResourceCommandRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      questionId: new FormControl(createResourceCommandRawValue.questionId),
    });
  }

  getCreateResourceCommand(form: CreateResourceCommandFormGroup): ICreateResourceCommand | NewCreateResourceCommand {
    return form.getRawValue() as ICreateResourceCommand | NewCreateResourceCommand;
  }

  resetForm(form: CreateResourceCommandFormGroup, createResourceCommand: CreateResourceCommandFormGroupInput): void {
    const createResourceCommandRawValue = { ...this.getFormDefaults(), ...createResourceCommand };
    form.reset(
      {
        ...createResourceCommandRawValue,
        id: { value: createResourceCommandRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CreateResourceCommandFormDefaults {
    return {
      id: null,
    };
  }
}
