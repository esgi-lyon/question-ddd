import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IAnswerSubmitCommand, NewAnswerSubmitCommand } from '../answer-submit-command.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAnswerSubmitCommand for edit and NewAnswerSubmitCommandFormGroupInput for create.
 */
type AnswerSubmitCommandFormGroupInput = IAnswerSubmitCommand | PartialWithRequiredKeyOf<NewAnswerSubmitCommand>;

type AnswerSubmitCommandFormDefaults = Pick<NewAnswerSubmitCommand, 'id'>;

type AnswerSubmitCommandFormGroupContent = {
  id: FormControl<IAnswerSubmitCommand['id'] | NewAnswerSubmitCommand['id']>;
  answer: FormControl<IAnswerSubmitCommand['answer']>;
};

export type AnswerSubmitCommandFormGroup = FormGroup<AnswerSubmitCommandFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AnswerSubmitCommandFormService {
  createAnswerSubmitCommandFormGroup(answerSubmitCommand: AnswerSubmitCommandFormGroupInput = { id: null }): AnswerSubmitCommandFormGroup {
    const answerSubmitCommandRawValue = {
      ...this.getFormDefaults(),
      ...answerSubmitCommand,
    };
    return new FormGroup<AnswerSubmitCommandFormGroupContent>({
      id: new FormControl(
        { value: answerSubmitCommandRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      answer: new FormControl(answerSubmitCommandRawValue.answer),
    });
  }

  getAnswerSubmitCommand(form: AnswerSubmitCommandFormGroup): IAnswerSubmitCommand | NewAnswerSubmitCommand {
    return form.getRawValue() as IAnswerSubmitCommand | NewAnswerSubmitCommand;
  }

  resetForm(form: AnswerSubmitCommandFormGroup, answerSubmitCommand: AnswerSubmitCommandFormGroupInput): void {
    const answerSubmitCommandRawValue = { ...this.getFormDefaults(), ...answerSubmitCommand };
    form.reset(
      {
        ...answerSubmitCommandRawValue,
        id: { value: answerSubmitCommandRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): AnswerSubmitCommandFormDefaults {
    return {
      id: null,
    };
  }
}
