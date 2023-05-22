import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { UserInfosFormService, UserInfosFormGroup } from './user-infos-form.service';
import { IUserInfos } from '../user-infos.model';
import { UserInfosService } from '../service/user-infos.service';
import { Roles } from 'app/entities/enumerations/roles.model';
import { UserStatus } from 'app/entities/enumerations/user-status.model';

@Component({
  selector: 'jhi-user-infos-update',
  templateUrl: './user-infos-update.component.html',
})
export class UserInfosUpdateComponent implements OnInit {
  isSaving = false;
  userInfos: IUserInfos | null = null;
  rolesValues = Object.keys(Roles);
  userStatusValues = Object.keys(UserStatus);

  editForm: UserInfosFormGroup = this.userInfosFormService.createUserInfosFormGroup();

  constructor(
    protected userInfosService: UserInfosService,
    protected userInfosFormService: UserInfosFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userInfos }) => {
      this.userInfos = userInfos;
      if (userInfos) {
        this.updateForm(userInfos);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userInfos = this.userInfosFormService.getUserInfos(this.editForm);
    if (userInfos.id !== null) {
      this.subscribeToSaveResponse(this.userInfosService.update(userInfos));
    } else {
      this.subscribeToSaveResponse(this.userInfosService.create(userInfos));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserInfos>>): void {
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

  protected updateForm(userInfos: IUserInfos): void {
    this.userInfos = userInfos;
    this.userInfosFormService.resetForm(this.editForm, userInfos);
  }
}
