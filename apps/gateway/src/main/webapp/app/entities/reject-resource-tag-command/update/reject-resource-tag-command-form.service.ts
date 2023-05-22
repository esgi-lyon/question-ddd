import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IRejectResourceTagCommand, NewRejectResourceTagCommand } from '../reject-resource-tag-command.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IRejectResourceTagCommand for edit and NewRejectResourceTagCommandFormGroupInput for create.
 */
type RejectResourceTagCommandFormGroupInput = IRejectResourceTagCommand | PartialWithRequiredKeyOf<NewRejectResourceTagCommand>;

type RejectResourceTagCommandFormDefaults = Pick<NewRejectResourceTagCommand, 'id'>;

type RejectResourceTagCommandFormGroupContent = {
  id: FormControl<IRejectResourceTagCommand['id'] | NewRejectResourceTagCommand['id']>;
  questionId: FormControl<IRejectResourceTagCommand['questionId']>;
};

export type RejectResourceTagCommandFormGroup = FormGroup<RejectResourceTagCommandFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class RejectResourceTagCommandFormService {
  createRejectResourceTagCommandFormGroup(
    rejectResourceTagCommand: RejectResourceTagCommandFormGroupInput = { id: null }
  ): RejectResourceTagCommandFormGroup {
    const rejectResourceTagCommandRawValue = {
      ...this.getFormDefaults(),
      ...rejectResourceTagCommand,
    };
    return new FormGroup<RejectResourceTagCommandFormGroupContent>({
      id: new FormControl(
        { value: rejectResourceTagCommandRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      questionId: new FormControl(rejectResourceTagCommandRawValue.questionId),
    });
  }

  getRejectResourceTagCommand(form: RejectResourceTagCommandFormGroup): IRejectResourceTagCommand | NewRejectResourceTagCommand {
    return form.getRawValue() as IRejectResourceTagCommand | NewRejectResourceTagCommand;
  }

  resetForm(form: RejectResourceTagCommandFormGroup, rejectResourceTagCommand: RejectResourceTagCommandFormGroupInput): void {
    const rejectResourceTagCommandRawValue = { ...this.getFormDefaults(), ...rejectResourceTagCommand };
    form.reset(
      {
        ...rejectResourceTagCommandRawValue,
        id: { value: rejectResourceTagCommandRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): RejectResourceTagCommandFormDefaults {
    return {
      id: null,
    };
  }
}
