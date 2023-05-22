import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICheckAnswerCommand, NewCheckAnswerCommand } from '../check-answer-command.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICheckAnswerCommand for edit and NewCheckAnswerCommandFormGroupInput for create.
 */
type CheckAnswerCommandFormGroupInput = ICheckAnswerCommand | PartialWithRequiredKeyOf<NewCheckAnswerCommand>;

type CheckAnswerCommandFormDefaults = Pick<NewCheckAnswerCommand, 'id'>;

type CheckAnswerCommandFormGroupContent = {
  id: FormControl<ICheckAnswerCommand['id'] | NewCheckAnswerCommand['id']>;
  answer: FormControl<ICheckAnswerCommand['answer']>;
};

export type CheckAnswerCommandFormGroup = FormGroup<CheckAnswerCommandFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CheckAnswerCommandFormService {
  createCheckAnswerCommandFormGroup(checkAnswerCommand: CheckAnswerCommandFormGroupInput = { id: null }): CheckAnswerCommandFormGroup {
    const checkAnswerCommandRawValue = {
      ...this.getFormDefaults(),
      ...checkAnswerCommand,
    };
    return new FormGroup<CheckAnswerCommandFormGroupContent>({
      id: new FormControl(
        { value: checkAnswerCommandRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      answer: new FormControl(checkAnswerCommandRawValue.answer),
    });
  }

  getCheckAnswerCommand(form: CheckAnswerCommandFormGroup): ICheckAnswerCommand | NewCheckAnswerCommand {
    return form.getRawValue() as ICheckAnswerCommand | NewCheckAnswerCommand;
  }

  resetForm(form: CheckAnswerCommandFormGroup, checkAnswerCommand: CheckAnswerCommandFormGroupInput): void {
    const checkAnswerCommandRawValue = { ...this.getFormDefaults(), ...checkAnswerCommand };
    form.reset(
      {
        ...checkAnswerCommandRawValue,
        id: { value: checkAnswerCommandRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CheckAnswerCommandFormDefaults {
    return {
      id: null,
    };
  }
}
