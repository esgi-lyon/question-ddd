import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IAwardPointForEvaluationCommand, NewAwardPointForEvaluationCommand } from '../award-point-for-evaluation-command.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAwardPointForEvaluationCommand for edit and NewAwardPointForEvaluationCommandFormGroupInput for create.
 */
type AwardPointForEvaluationCommandFormGroupInput =
  | IAwardPointForEvaluationCommand
  | PartialWithRequiredKeyOf<NewAwardPointForEvaluationCommand>;

type AwardPointForEvaluationCommandFormDefaults = Pick<NewAwardPointForEvaluationCommand, 'id'>;

type AwardPointForEvaluationCommandFormGroupContent = {
  id: FormControl<IAwardPointForEvaluationCommand['id'] | NewAwardPointForEvaluationCommand['id']>;
  evaluation: FormControl<IAwardPointForEvaluationCommand['evaluation']>;
};

export type AwardPointForEvaluationCommandFormGroup = FormGroup<AwardPointForEvaluationCommandFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AwardPointForEvaluationCommandFormService {
  createAwardPointForEvaluationCommandFormGroup(
    awardPointForEvaluationCommand: AwardPointForEvaluationCommandFormGroupInput = { id: null }
  ): AwardPointForEvaluationCommandFormGroup {
    const awardPointForEvaluationCommandRawValue = {
      ...this.getFormDefaults(),
      ...awardPointForEvaluationCommand,
    };
    return new FormGroup<AwardPointForEvaluationCommandFormGroupContent>({
      id: new FormControl(
        { value: awardPointForEvaluationCommandRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      evaluation: new FormControl(awardPointForEvaluationCommandRawValue.evaluation),
    });
  }

  getAwardPointForEvaluationCommand(
    form: AwardPointForEvaluationCommandFormGroup
  ): IAwardPointForEvaluationCommand | NewAwardPointForEvaluationCommand {
    return form.getRawValue() as IAwardPointForEvaluationCommand | NewAwardPointForEvaluationCommand;
  }

  resetForm(
    form: AwardPointForEvaluationCommandFormGroup,
    awardPointForEvaluationCommand: AwardPointForEvaluationCommandFormGroupInput
  ): void {
    const awardPointForEvaluationCommandRawValue = { ...this.getFormDefaults(), ...awardPointForEvaluationCommand };
    form.reset(
      {
        ...awardPointForEvaluationCommandRawValue,
        id: { value: awardPointForEvaluationCommandRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): AwardPointForEvaluationCommandFormDefaults {
    return {
      id: null,
    };
  }
}
