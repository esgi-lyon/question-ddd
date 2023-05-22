import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { UserPreferencesFormService, UserPreferencesFormGroup } from './user-preferences-form.service';
import { IUserPreferences } from '../user-preferences.model';
import { UserPreferencesService } from '../service/user-preferences.service';
import { IUserWithPreferencesId } from 'app/entities/user-with-preferences-id/user-with-preferences-id.model';
import { UserWithPreferencesIdService } from 'app/entities/user-with-preferences-id/service/user-with-preferences-id.service';

@Component({
  selector: 'jhi-user-preferences-update',
  templateUrl: './user-preferences-update.component.html',
})
export class UserPreferencesUpdateComponent implements OnInit {
  isSaving = false;
  userPreferences: IUserPreferences | null = null;

  userWithPreferencesIdsSharedCollection: IUserWithPreferencesId[] = [];

  editForm: UserPreferencesFormGroup = this.userPreferencesFormService.createUserPreferencesFormGroup();

  constructor(
    protected userPreferencesService: UserPreferencesService,
    protected userPreferencesFormService: UserPreferencesFormService,
    protected userWithPreferencesIdService: UserWithPreferencesIdService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareUserWithPreferencesId = (o1: IUserWithPreferencesId | null, o2: IUserWithPreferencesId | null): boolean =>
    this.userWithPreferencesIdService.compareUserWithPreferencesId(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userPreferences }) => {
      this.userPreferences = userPreferences;
      if (userPreferences) {
        this.updateForm(userPreferences);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userPreferences = this.userPreferencesFormService.getUserPreferences(this.editForm);
    if (userPreferences.id !== null) {
      this.subscribeToSaveResponse(this.userPreferencesService.update(userPreferences));
    } else {
      this.subscribeToSaveResponse(this.userPreferencesService.create(userPreferences));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserPreferences>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(userPreferences: IUserPreferences): void {
    this.userPreferences = userPreferences;
    this.userPreferencesFormService.resetForm(this.editForm, userPreferences);

    this.userWithPreferencesIdsSharedCollection =
      this.userWithPreferencesIdService.addUserWithPreferencesIdToCollectionIfMissing<IUserWithPreferencesId>(
        this.userWithPreferencesIdsSharedCollection,
        userPreferences.user
      );
  }

  protected loadRelationshipsOptions(): void {
    this.userWithPreferencesIdService
      .query()
      .pipe(map((res: HttpResponse<IUserWithPreferencesId[]>) => res.body ?? []))
      .pipe(
        map((userWithPreferencesIds: IUserWithPreferencesId[]) =>
          this.userWithPreferencesIdService.addUserWithPreferencesIdToCollectionIfMissing<IUserWithPreferencesId>(
            userWithPreferencesIds,
            this.userPreferences?.user
          )
        )
      )
      .subscribe(
        (userWithPreferencesIds: IUserWithPreferencesId[]) => (this.userWithPreferencesIdsSharedCollection = userWithPreferencesIds)
      );
  }
}
