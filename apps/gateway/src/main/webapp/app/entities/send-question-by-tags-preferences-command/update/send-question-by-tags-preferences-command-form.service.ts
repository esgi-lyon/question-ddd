import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import {
  ISendQuestionByTagsPreferencesCommand,
  NewSendQuestionByTagsPreferencesCommand,
} from '../send-question-by-tags-preferences-command.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISendQuestionByTagsPreferencesCommand for edit and NewSendQuestionByTagsPreferencesCommandFormGroupInput for create.
 */
type SendQuestionByTagsPreferencesCommandFormGroupInput =
  | ISendQuestionByTagsPreferencesCommand
  | PartialWithRequiredKeyOf<NewSendQuestionByTagsPreferencesCommand>;

type SendQuestionByTagsPreferencesCommandFormDefaults = Pick<NewSendQuestionByTagsPreferencesCommand, 'id'>;

type SendQuestionByTagsPreferencesCommandFormGroupContent = {
  id: FormControl<ISendQuestionByTagsPreferencesCommand['id'] | NewSendQuestionByTagsPreferencesCommand['id']>;
  questionSent: FormControl<ISendQuestionByTagsPreferencesCommand['questionSent']>;
};

export type SendQuestionByTagsPreferencesCommandFormGroup = FormGroup<SendQuestionByTagsPreferencesCommandFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SendQuestionByTagsPreferencesCommandFormService {
  createSendQuestionByTagsPreferencesCommandFormGroup(
    sendQuestionByTagsPreferencesCommand: SendQuestionByTagsPreferencesCommandFormGroupInput = { id: null }
  ): SendQuestionByTagsPreferencesCommandFormGroup {
    const sendQuestionByTagsPreferencesCommandRawValue = {
      ...this.getFormDefaults(),
      ...sendQuestionByTagsPreferencesCommand,
    };
    return new FormGroup<SendQuestionByTagsPreferencesCommandFormGroupContent>({
      id: new FormControl(
        { value: sendQuestionByTagsPreferencesCommandRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      questionSent: new FormControl(sendQuestionByTagsPreferencesCommandRawValue.questionSent),
    });
  }

  getSendQuestionByTagsPreferencesCommand(
    form: SendQuestionByTagsPreferencesCommandFormGroup
  ): ISendQuestionByTagsPreferencesCommand | NewSendQuestionByTagsPreferencesCommand {
    return form.getRawValue() as ISendQuestionByTagsPreferencesCommand | NewSendQuestionByTagsPreferencesCommand;
  }

  resetForm(
    form: SendQuestionByTagsPreferencesCommandFormGroup,
    sendQuestionByTagsPreferencesCommand: SendQuestionByTagsPreferencesCommandFormGroupInput
  ): void {
    const sendQuestionByTagsPreferencesCommandRawValue = { ...this.getFormDefaults(), ...sendQuestionByTagsPreferencesCommand };
    form.reset(
      {
        ...sendQuestionByTagsPreferencesCommandRawValue,
        id: { value: sendQuestionByTagsPreferencesCommandRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SendQuestionByTagsPreferencesCommandFormDefaults {
    return {
      id: null,
    };
  }
}
