import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IPointAwardRule, NewPointAwardRule } from '../point-award-rule.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IPointAwardRule for edit and NewPointAwardRuleFormGroupInput for create.
 */
type PointAwardRuleFormGroupInput = IPointAwardRule | PartialWithRequiredKeyOf<NewPointAwardRule>;

type PointAwardRuleFormDefaults = Pick<NewPointAwardRule, 'id'>;

type PointAwardRuleFormGroupContent = {
  id: FormControl<IPointAwardRule['id'] | NewPointAwardRule['id']>;
  scoreEvolution: FormControl<IPointAwardRule['scoreEvolution']>;
  difficultyLevel: FormControl<IPointAwardRule['difficultyLevel']>;
  userLevel: FormControl<IPointAwardRule['userLevel']>;
};

export type PointAwardRuleFormGroup = FormGroup<PointAwardRuleFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class PointAwardRuleFormService {
  createPointAwardRuleFormGroup(pointAwardRule: PointAwardRuleFormGroupInput = { id: null }): PointAwardRuleFormGroup {
    const pointAwardRuleRawValue = {
      ...this.getFormDefaults(),
      ...pointAwardRule,
    };
    return new FormGroup<PointAwardRuleFormGroupContent>({
      id: new FormControl(
        { value: pointAwardRuleRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      scoreEvolution: new FormControl(pointAwardRuleRawValue.scoreEvolution),
      difficultyLevel: new FormControl(pointAwardRuleRawValue.difficultyLevel),
      userLevel: new FormControl(pointAwardRuleRawValue.userLevel),
    });
  }

  getPointAwardRule(form: PointAwardRuleFormGroup): IPointAwardRule | NewPointAwardRule {
    return form.getRawValue() as IPointAwardRule | NewPointAwardRule;
  }

  resetForm(form: PointAwardRuleFormGroup, pointAwardRule: PointAwardRuleFormGroupInput): void {
    const pointAwardRuleRawValue = { ...this.getFormDefaults(), ...pointAwardRule };
    form.reset(
      {
        ...pointAwardRuleRawValue,
        id: { value: pointAwardRuleRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): PointAwardRuleFormDefaults {
    return {
      id: null,
    };
  }
}
