import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IValidateResourceTagLinkageCommand, NewValidateResourceTagLinkageCommand } from '../validate-resource-tag-linkage-command.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IValidateResourceTagLinkageCommand for edit and NewValidateResourceTagLinkageCommandFormGroupInput for create.
 */
type ValidateResourceTagLinkageCommandFormGroupInput =
  | IValidateResourceTagLinkageCommand
  | PartialWithRequiredKeyOf<NewValidateResourceTagLinkageCommand>;

type ValidateResourceTagLinkageCommandFormDefaults = Pick<NewValidateResourceTagLinkageCommand, 'id'>;

type ValidateResourceTagLinkageCommandFormGroupContent = {
  id: FormControl<IValidateResourceTagLinkageCommand['id'] | NewValidateResourceTagLinkageCommand['id']>;
  questionId: FormControl<IValidateResourceTagLinkageCommand['questionId']>;
};

export type ValidateResourceTagLinkageCommandFormGroup = FormGroup<ValidateResourceTagLinkageCommandFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ValidateResourceTagLinkageCommandFormService {
  createValidateResourceTagLinkageCommandFormGroup(
    validateResourceTagLinkageCommand: ValidateResourceTagLinkageCommandFormGroupInput = { id: null }
  ): ValidateResourceTagLinkageCommandFormGroup {
    const validateResourceTagLinkageCommandRawValue = {
      ...this.getFormDefaults(),
      ...validateResourceTagLinkageCommand,
    };
    return new FormGroup<ValidateResourceTagLinkageCommandFormGroupContent>({
      id: new FormControl(
        { value: validateResourceTagLinkageCommandRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      questionId: new FormControl(validateResourceTagLinkageCommandRawValue.questionId),
    });
  }

  getValidateResourceTagLinkageCommand(
    form: ValidateResourceTagLinkageCommandFormGroup
  ): IValidateResourceTagLinkageCommand | NewValidateResourceTagLinkageCommand {
    return form.getRawValue() as IValidateResourceTagLinkageCommand | NewValidateResourceTagLinkageCommand;
  }

  resetForm(
    form: ValidateResourceTagLinkageCommandFormGroup,
    validateResourceTagLinkageCommand: ValidateResourceTagLinkageCommandFormGroupInput
  ): void {
    const validateResourceTagLinkageCommandRawValue = { ...this.getFormDefaults(), ...validateResourceTagLinkageCommand };
    form.reset(
      {
        ...validateResourceTagLinkageCommandRawValue,
        id: { value: validateResourceTagLinkageCommandRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ValidateResourceTagLinkageCommandFormDefaults {
    return {
      id: null,
    };
  }
}
