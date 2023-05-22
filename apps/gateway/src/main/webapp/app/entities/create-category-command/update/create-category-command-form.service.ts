import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICreateCategoryCommand, NewCreateCategoryCommand } from '../create-category-command.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICreateCategoryCommand for edit and NewCreateCategoryCommandFormGroupInput for create.
 */
type CreateCategoryCommandFormGroupInput = ICreateCategoryCommand | PartialWithRequiredKeyOf<NewCreateCategoryCommand>;

type CreateCategoryCommandFormDefaults = Pick<NewCreateCategoryCommand, 'id'>;

type CreateCategoryCommandFormGroupContent = {
  id: FormControl<ICreateCategoryCommand['id'] | NewCreateCategoryCommand['id']>;
  category: FormControl<ICreateCategoryCommand['category']>;
};

export type CreateCategoryCommandFormGroup = FormGroup<CreateCategoryCommandFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CreateCategoryCommandFormService {
  createCreateCategoryCommandFormGroup(
    createCategoryCommand: CreateCategoryCommandFormGroupInput = { id: null }
  ): CreateCategoryCommandFormGroup {
    const createCategoryCommandRawValue = {
      ...this.getFormDefaults(),
      ...createCategoryCommand,
    };
    return new FormGroup<CreateCategoryCommandFormGroupContent>({
      id: new FormControl(
        { value: createCategoryCommandRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      category: new FormControl(createCategoryCommandRawValue.category),
    });
  }

  getCreateCategoryCommand(form: CreateCategoryCommandFormGroup): ICreateCategoryCommand | NewCreateCategoryCommand {
    return form.getRawValue() as ICreateCategoryCommand | NewCreateCategoryCommand;
  }

  resetForm(form: CreateCategoryCommandFormGroup, createCategoryCommand: CreateCategoryCommandFormGroupInput): void {
    const createCategoryCommandRawValue = { ...this.getFormDefaults(), ...createCategoryCommand };
    form.reset(
      {
        ...createCategoryCommandRawValue,
        id: { value: createCategoryCommandRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CreateCategoryCommandFormDefaults {
    return {
      id: null,
    };
  }
}
