import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { PointAwardRuleFormService, PointAwardRuleFormGroup } from './point-award-rule-form.service';
import { IPointAwardRule } from '../point-award-rule.model';
import { PointAwardRuleService } from '../service/point-award-rule.service';
import { DifficultyLevel } from 'app/entities/enumerations/difficulty-level.model';
import { UserLevel } from 'app/entities/enumerations/user-level.model';

@Component({
  selector: 'jhi-point-award-rule-update',
  templateUrl: './point-award-rule-update.component.html',
})
export class PointAwardRuleUpdateComponent implements OnInit {
  isSaving = false;
  pointAwardRule: IPointAwardRule | null = null;
  difficultyLevelValues = Object.keys(DifficultyLevel);
  userLevelValues = Object.keys(UserLevel);

  editForm: PointAwardRuleFormGroup = this.pointAwardRuleFormService.createPointAwardRuleFormGroup();

  constructor(
    protected pointAwardRuleService: PointAwardRuleService,
    protected pointAwardRuleFormService: PointAwardRuleFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pointAwardRule }) => {
      this.pointAwardRule = pointAwardRule;
      if (pointAwardRule) {
        this.updateForm(pointAwardRule);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const pointAwardRule = this.pointAwardRuleFormService.getPointAwardRule(this.editForm);
    if (pointAwardRule.id !== null) {
      this.subscribeToSaveResponse(this.pointAwardRuleService.update(pointAwardRule));
    } else {
      this.subscribeToSaveResponse(this.pointAwardRuleService.create(pointAwardRule));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPointAwardRule>>): void {
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

  protected updateForm(pointAwardRule: IPointAwardRule): void {
    this.pointAwardRule = pointAwardRule;
    this.pointAwardRuleFormService.resetForm(this.editForm, pointAwardRule);
  }
}
