import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IEvaluation, NewEvaluation } from '../evaluation.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IEvaluation for edit and NewEvaluationFormGroupInput for create.
 */
type EvaluationFormGroupInput = IEvaluation | PartialWithRequiredKeyOf<NewEvaluation>;

type EvaluationFormDefaults = Pick<NewEvaluation, 'id'>;

type EvaluationFormGroupContent = {
  id: FormControl<IEvaluation['id'] | NewEvaluation['id']>;
  score: FormControl<IEvaluation['score']>;
  status: FormControl<IEvaluation['status']>;
  answeredQuestionDifficultyLevel: FormControl<IEvaluation['answeredQuestionDifficultyLevel']>;
  tag: FormControl<IEvaluation['tag']>;
  question: FormControl<IEvaluation['question']>;
  user: FormControl<IEvaluation['user']>;
};

export type EvaluationFormGroup = FormGroup<EvaluationFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class EvaluationFormService {
  createEvaluationFormGroup(evaluation: EvaluationFormGroupInput = { id: null }): EvaluationFormGroup {
    const evaluationRawValue = {
      ...this.getFormDefaults(),
      ...evaluation,
    };
    return new FormGroup<EvaluationFormGroupContent>({
      id: new FormControl(
        { value: evaluationRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      score: new FormControl(evaluationRawValue.score),
      status: new FormControl(evaluationRawValue.status),
      answeredQuestionDifficultyLevel: new FormControl(evaluationRawValue.answeredQuestionDifficultyLevel),
      tag: new FormControl(evaluationRawValue.tag),
      question: new FormControl(evaluationRawValue.question),
      user: new FormControl(evaluationRawValue.user),
    });
  }

  getEvaluation(form: EvaluationFormGroup): IEvaluation | NewEvaluation {
    return form.getRawValue() as IEvaluation | NewEvaluation;
  }

  resetForm(form: EvaluationFormGroup, evaluation: EvaluationFormGroupInput): void {
    const evaluationRawValue = { ...this.getFormDefaults(), ...evaluation };
    form.reset(
      {
        ...evaluationRawValue,
        id: { value: evaluationRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): EvaluationFormDefaults {
    return {
      id: null,
    };
  }
}
