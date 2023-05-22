import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ITagChoicesListCommand, NewTagChoicesListCommand } from '../tag-choices-list-command.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ITagChoicesListCommand for edit and NewTagChoicesListCommandFormGroupInput for create.
 */
type TagChoicesListCommandFormGroupInput = ITagChoicesListCommand | PartialWithRequiredKeyOf<NewTagChoicesListCommand>;

type TagChoicesListCommandFormDefaults = Pick<NewTagChoicesListCommand, 'id'>;

type TagChoicesListCommandFormGroupContent = {
  id: FormControl<ITagChoicesListCommand['id'] | NewTagChoicesListCommand['id']>;
};

export type TagChoicesListCommandFormGroup = FormGroup<TagChoicesListCommandFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class TagChoicesListCommandFormService {
  createTagChoicesListCommandFormGroup(
    tagChoicesListCommand: TagChoicesListCommandFormGroupInput = { id: null }
  ): TagChoicesListCommandFormGroup {
    const tagChoicesListCommandRawValue = {
      ...this.getFormDefaults(),
      ...tagChoicesListCommand,
    };
    return new FormGroup<TagChoicesListCommandFormGroupContent>({
      id: new FormControl(
        { value: tagChoicesListCommandRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
    });
  }

  getTagChoicesListCommand(form: TagChoicesListCommandFormGroup): ITagChoicesListCommand | NewTagChoicesListCommand {
    if (form.controls.id.disabled) {
      // form.value returns id with null value for FormGroup with only one FormControl
      return {};
    }
    return form.getRawValue() as ITagChoicesListCommand | NewTagChoicesListCommand;
  }

  resetForm(form: TagChoicesListCommandFormGroup, tagChoicesListCommand: TagChoicesListCommandFormGroupInput): void {
    const tagChoicesListCommandRawValue = { ...this.getFormDefaults(), ...tagChoicesListCommand };
    form.reset(
      {
        ...tagChoicesListCommandRawValue,
        id: { value: tagChoicesListCommandRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): TagChoicesListCommandFormDefaults {
    return {
      id: null,
    };
  }
}
