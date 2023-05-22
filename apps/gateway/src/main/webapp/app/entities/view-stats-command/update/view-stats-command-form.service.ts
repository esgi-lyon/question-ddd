import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IViewStatsCommand, NewViewStatsCommand } from '../view-stats-command.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IViewStatsCommand for edit and NewViewStatsCommandFormGroupInput for create.
 */
type ViewStatsCommandFormGroupInput = IViewStatsCommand | PartialWithRequiredKeyOf<NewViewStatsCommand>;

type ViewStatsCommandFormDefaults = Pick<NewViewStatsCommand, 'id'>;

type ViewStatsCommandFormGroupContent = {
  id: FormControl<IViewStatsCommand['id'] | NewViewStatsCommand['id']>;
  user: FormControl<IViewStatsCommand['user']>;
  question: FormControl<IViewStatsCommand['question']>;
  tag: FormControl<IViewStatsCommand['tag']>;
};

export type ViewStatsCommandFormGroup = FormGroup<ViewStatsCommandFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ViewStatsCommandFormService {
  createViewStatsCommandFormGroup(viewStatsCommand: ViewStatsCommandFormGroupInput = { id: null }): ViewStatsCommandFormGroup {
    const viewStatsCommandRawValue = {
      ...this.getFormDefaults(),
      ...viewStatsCommand,
    };
    return new FormGroup<ViewStatsCommandFormGroupContent>({
      id: new FormControl(
        { value: viewStatsCommandRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      user: new FormControl(viewStatsCommandRawValue.user),
      question: new FormControl(viewStatsCommandRawValue.question),
      tag: new FormControl(viewStatsCommandRawValue.tag),
    });
  }

  getViewStatsCommand(form: ViewStatsCommandFormGroup): IViewStatsCommand | NewViewStatsCommand {
    return form.getRawValue() as IViewStatsCommand | NewViewStatsCommand;
  }

  resetForm(form: ViewStatsCommandFormGroup, viewStatsCommand: ViewStatsCommandFormGroupInput): void {
    const viewStatsCommandRawValue = { ...this.getFormDefaults(), ...viewStatsCommand };
    form.reset(
      {
        ...viewStatsCommandRawValue,
        id: { value: viewStatsCommandRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ViewStatsCommandFormDefaults {
    return {
      id: null,
    };
  }
}
