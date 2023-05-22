import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ILeaderBoard, NewLeaderBoard } from '../leader-board.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ILeaderBoard for edit and NewLeaderBoardFormGroupInput for create.
 */
type LeaderBoardFormGroupInput = ILeaderBoard | PartialWithRequiredKeyOf<NewLeaderBoard>;

type LeaderBoardFormDefaults = Pick<NewLeaderBoard, 'id'>;

type LeaderBoardFormGroupContent = {
  id: FormControl<ILeaderBoard['id'] | NewLeaderBoard['id']>;
  difficultyLevel: FormControl<ILeaderBoard['difficultyLevel']>;
  tagId: FormControl<ILeaderBoard['tagId']>;
};

export type LeaderBoardFormGroup = FormGroup<LeaderBoardFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class LeaderBoardFormService {
  createLeaderBoardFormGroup(leaderBoard: LeaderBoardFormGroupInput = { id: null }): LeaderBoardFormGroup {
    const leaderBoardRawValue = {
      ...this.getFormDefaults(),
      ...leaderBoard,
    };
    return new FormGroup<LeaderBoardFormGroupContent>({
      id: new FormControl(
        { value: leaderBoardRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      difficultyLevel: new FormControl(leaderBoardRawValue.difficultyLevel),
      tagId: new FormControl(leaderBoardRawValue.tagId),
    });
  }

  getLeaderBoard(form: LeaderBoardFormGroup): ILeaderBoard | NewLeaderBoard {
    return form.getRawValue() as ILeaderBoard | NewLeaderBoard;
  }

  resetForm(form: LeaderBoardFormGroup, leaderBoard: LeaderBoardFormGroupInput): void {
    const leaderBoardRawValue = { ...this.getFormDefaults(), ...leaderBoard };
    form.reset(
      {
        ...leaderBoardRawValue,
        id: { value: leaderBoardRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): LeaderBoardFormDefaults {
    return {
      id: null,
    };
  }
}
