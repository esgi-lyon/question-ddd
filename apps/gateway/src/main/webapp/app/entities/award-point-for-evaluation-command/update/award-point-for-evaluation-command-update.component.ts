import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import {
  AwardPointForEvaluationCommandFormService,
  AwardPointForEvaluationCommandFormGroup,
} from './award-point-for-evaluation-command-form.service';
import { IAwardPointForEvaluationCommand } from '../award-point-for-evaluation-command.model';
import { AwardPointForEvaluationCommandService } from '../service/award-point-for-evaluation-command.service';
import { IEvaluation } from 'app/entities/EvaluationContext/evaluation/evaluation.model';
import { EvaluationService } from 'app/entities/EvaluationContext/evaluation/service/evaluation.service';

@Component({
  selector: 'jhi-award-point-for-evaluation-command-update',
  templateUrl: './award-point-for-evaluation-command-update.component.html',
})
export class AwardPointForEvaluationCommandUpdateComponent implements OnInit {
  isSaving = false;
  awardPointForEvaluationCommand: IAwardPointForEvaluationCommand | null = null;

  evaluationsSharedCollection: IEvaluation[] = [];

  editForm: AwardPointForEvaluationCommandFormGroup =
    this.awardPointForEvaluationCommandFormService.createAwardPointForEvaluationCommandFormGroup();

  constructor(
    protected awardPointForEvaluationCommandService: AwardPointForEvaluationCommandService,
    protected awardPointForEvaluationCommandFormService: AwardPointForEvaluationCommandFormService,
    protected evaluationService: EvaluationService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareEvaluation = (o1: IEvaluation | null, o2: IEvaluation | null): boolean => this.evaluationService.compareEvaluation(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ awardPointForEvaluationCommand }) => {
      this.awardPointForEvaluationCommand = awardPointForEvaluationCommand;
      if (awardPointForEvaluationCommand) {
        this.updateForm(awardPointForEvaluationCommand);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const awardPointForEvaluationCommand = this.awardPointForEvaluationCommandFormService.getAwardPointForEvaluationCommand(this.editForm);
    if (awardPointForEvaluationCommand.id !== null) {
      this.subscribeToSaveResponse(this.awardPointForEvaluationCommandService.update(awardPointForEvaluationCommand));
    } else {
      this.subscribeToSaveResponse(this.awardPointForEvaluationCommandService.create(awardPointForEvaluationCommand));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAwardPointForEvaluationCommand>>): void {
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

  protected updateForm(awardPointForEvaluationCommand: IAwardPointForEvaluationCommand): void {
    this.awardPointForEvaluationCommand = awardPointForEvaluationCommand;
    this.awardPointForEvaluationCommandFormService.resetForm(this.editForm, awardPointForEvaluationCommand);

    this.evaluationsSharedCollection = this.evaluationService.addEvaluationToCollectionIfMissing<IEvaluation>(
      this.evaluationsSharedCollection,
      awardPointForEvaluationCommand.evaluation
    );
  }

  protected loadRelationshipsOptions(): void {
    this.evaluationService
      .query()
      .pipe(map((res: HttpResponse<IEvaluation[]>) => res.body ?? []))
      .pipe(
        map((evaluations: IEvaluation[]) =>
          this.evaluationService.addEvaluationToCollectionIfMissing<IEvaluation>(
            evaluations,
            this.awardPointForEvaluationCommand?.evaluation
          )
        )
      )
      .subscribe((evaluations: IEvaluation[]) => (this.evaluationsSharedCollection = evaluations));
  }
}
