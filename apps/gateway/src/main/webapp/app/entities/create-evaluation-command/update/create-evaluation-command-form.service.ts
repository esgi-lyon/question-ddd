import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICreateEvaluationCommand, NewCreateEvaluationCommand } from '../create-evaluation-command.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICreateEvaluationCommand for edit and NewCreateEvaluationCommandFormGroupInput for create.
 */
type CreateEvaluationCommandFormGroupInput = ICreateEvaluationCommand | PartialWithRequiredKeyOf<NewCreateEvaluationCommand>;

type CreateEvaluationCommandFormDefaults = Pick<NewCreateEvaluationCommand, 'id'>;

type CreateEvaluationCommandFormGroupContent = {
  id: FormControl<ICreateEvaluationCommand['id'] | NewCreateEvaluationCommand['id']>;
  answer: FormControl<ICreateEvaluationCommand['answer']>;
};

export type CreateEvaluationCommandFormGroup = FormGroup<CreateEvaluationCommandFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CreateEvaluationCommandFormService {
  createCreateEvaluationCommandFormGroup(
    createEvaluationCommand: CreateEvaluationCommandFormGroupInput = { id: null }
  ): CreateEvaluationCommandFormGroup {
    const createEvaluationCommandRawValue = {
      ...this.getFormDefaults(),
      ...createEvaluationCommand,
    };
    return new FormGroup<CreateEvaluationCommandFormGroupContent>({
      id: new FormControl(
        { value: createEvaluationCommandRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      answer: new FormControl(createEvaluationCommandRawValue.answer),
    });
  }

  getCreateEvaluationCommand(form: CreateEvaluationCommandFormGroup): ICreateEvaluationCommand | NewCreateEvaluationCommand {
    return form.getRawValue() as ICreateEvaluationCommand | NewCreateEvaluationCommand;
  }

  resetForm(form: CreateEvaluationCommandFormGroup, createEvaluationCommand: CreateEvaluationCommandFormGroupInput): void {
    const createEvaluationCommandRawValue = { ...this.getFormDefaults(), ...createEvaluationCommand };
    form.reset(
      {
        ...createEvaluationCommandRawValue,
        id: { value: createEvaluationCommandRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CreateEvaluationCommandFormDefaults {
    return {
      id: null,
    };
  }
}
