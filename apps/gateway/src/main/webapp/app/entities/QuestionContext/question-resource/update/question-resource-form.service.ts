import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IQuestionResource, NewQuestionResource } from '../question-resource.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IQuestionResource for edit and NewQuestionResourceFormGroupInput for create.
 */
type QuestionResourceFormGroupInput = IQuestionResource | PartialWithRequiredKeyOf<NewQuestionResource>;

type QuestionResourceFormDefaults = Pick<NewQuestionResource, 'id'>;

type QuestionResourceFormGroupContent = {
  id: FormControl<IQuestionResource['id'] | NewQuestionResource['id']>;
  questionContent: FormControl<IQuestionResource['questionContent']>;
  tag: FormControl<IQuestionResource['tag']>;
  questionState: FormControl<IQuestionResource['questionState']>;
  resourceType: FormControl<IQuestionResource['resourceType']>;
};

export type QuestionResourceFormGroup = FormGroup<QuestionResourceFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class QuestionResourceFormService {
  createQuestionResourceFormGroup(questionResource: QuestionResourceFormGroupInput = { id: null }): QuestionResourceFormGroup {
    const questionResourceRawValue = {
      ...this.getFormDefaults(),
      ...questionResource,
    };
    return new FormGroup<QuestionResourceFormGroupContent>({
      id: new FormControl(
        { value: questionResourceRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      questionContent: new FormControl(questionResourceRawValue.questionContent),
      tag: new FormControl(questionResourceRawValue.tag),
      questionState: new FormControl(questionResourceRawValue.questionState),
      resourceType: new FormControl(questionResourceRawValue.resourceType),
    });
  }

  getQuestionResource(form: QuestionResourceFormGroup): IQuestionResource | NewQuestionResource {
    return form.getRawValue() as IQuestionResource | NewQuestionResource;
  }

  resetForm(form: QuestionResourceFormGroup, questionResource: QuestionResourceFormGroupInput): void {
    const questionResourceRawValue = { ...this.getFormDefaults(), ...questionResource };
    form.reset(
      {
        ...questionResourceRawValue,
        id: { value: questionResourceRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): QuestionResourceFormDefaults {
    return {
      id: null,
    };
  }
}
