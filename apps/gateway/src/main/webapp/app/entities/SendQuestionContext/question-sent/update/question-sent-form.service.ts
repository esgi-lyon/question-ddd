import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IQuestionSent, NewQuestionSent } from '../question-sent.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IQuestionSent for edit and NewQuestionSentFormGroupInput for create.
 */
type QuestionSentFormGroupInput = IQuestionSent | PartialWithRequiredKeyOf<NewQuestionSent>;

type QuestionSentFormDefaults = Pick<NewQuestionSent, 'id'>;

type QuestionSentFormGroupContent = {
  id: FormControl<IQuestionSent['id'] | NewQuestionSent['id']>;
  resource: FormControl<IQuestionSent['resource']>;
  sentDate: FormControl<IQuestionSent['sentDate']>;
  viewedDate: FormControl<IQuestionSent['viewedDate']>;
  answeredDate: FormControl<IQuestionSent['answeredDate']>;
  status: FormControl<IQuestionSent['status']>;
};

export type QuestionSentFormGroup = FormGroup<QuestionSentFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class QuestionSentFormService {
  createQuestionSentFormGroup(questionSent: QuestionSentFormGroupInput = { id: null }): QuestionSentFormGroup {
    const questionSentRawValue = {
      ...this.getFormDefaults(),
      ...questionSent,
    };
    return new FormGroup<QuestionSentFormGroupContent>({
      id: new FormControl(
        { value: questionSentRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      resource: new FormControl(questionSentRawValue.resource),
      sentDate: new FormControl(questionSentRawValue.sentDate),
      viewedDate: new FormControl(questionSentRawValue.viewedDate),
      answeredDate: new FormControl(questionSentRawValue.answeredDate),
      status: new FormControl(questionSentRawValue.status),
    });
  }

  getQuestionSent(form: QuestionSentFormGroup): IQuestionSent | NewQuestionSent {
    return form.getRawValue() as IQuestionSent | NewQuestionSent;
  }

  resetForm(form: QuestionSentFormGroup, questionSent: QuestionSentFormGroupInput): void {
    const questionSentRawValue = { ...this.getFormDefaults(), ...questionSent };
    form.reset(
      {
        ...questionSentRawValue,
        id: { value: questionSentRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): QuestionSentFormDefaults {
    return {
      id: null,
    };
  }
}
