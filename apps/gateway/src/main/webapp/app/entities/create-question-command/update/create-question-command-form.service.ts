import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICreateQuestionCommand, NewCreateQuestionCommand } from '../create-question-command.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICreateQuestionCommand for edit and NewCreateQuestionCommandFormGroupInput for create.
 */
type CreateQuestionCommandFormGroupInput = ICreateQuestionCommand | PartialWithRequiredKeyOf<NewCreateQuestionCommand>;

type CreateQuestionCommandFormDefaults = Pick<NewCreateQuestionCommand, 'id'>;

type CreateQuestionCommandFormGroupContent = {
  id: FormControl<ICreateQuestionCommand['id'] | NewCreateQuestionCommand['id']>;
  resource: FormControl<ICreateQuestionCommand['resource']>;
};

export type CreateQuestionCommandFormGroup = FormGroup<CreateQuestionCommandFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CreateQuestionCommandFormService {
  createCreateQuestionCommandFormGroup(
    createQuestionCommand: CreateQuestionCommandFormGroupInput = { id: null }
  ): CreateQuestionCommandFormGroup {
    const createQuestionCommandRawValue = {
      ...this.getFormDefaults(),
      ...createQuestionCommand,
    };
    return new FormGroup<CreateQuestionCommandFormGroupContent>({
      id: new FormControl(
        { value: createQuestionCommandRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      resource: new FormControl(createQuestionCommandRawValue.resource),
    });
  }

  getCreateQuestionCommand(form: CreateQuestionCommandFormGroup): ICreateQuestionCommand | NewCreateQuestionCommand {
    return form.getRawValue() as ICreateQuestionCommand | NewCreateQuestionCommand;
  }

  resetForm(form: CreateQuestionCommandFormGroup, createQuestionCommand: CreateQuestionCommandFormGroupInput): void {
    const createQuestionCommandRawValue = { ...this.getFormDefaults(), ...createQuestionCommand };
    form.reset(
      {
        ...createQuestionCommandRawValue,
        id: { value: createQuestionCommandRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CreateQuestionCommandFormDefaults {
    return {
      id: null,
    };
  }
}
