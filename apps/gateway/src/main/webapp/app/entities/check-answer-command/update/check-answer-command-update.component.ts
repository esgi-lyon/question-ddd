import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { CheckAnswerCommandFormService, CheckAnswerCommandFormGroup } from './check-answer-command-form.service';
import { ICheckAnswerCommand } from '../check-answer-command.model';
import { CheckAnswerCommandService } from '../service/check-answer-command.service';
import { IEvaluatedAnswer } from 'app/entities/evaluated-answer/evaluated-answer.model';
import { EvaluatedAnswerService } from 'app/entities/evaluated-answer/service/evaluated-answer.service';

@Component({
  selector: 'jhi-check-answer-command-update',
  templateUrl: './check-answer-command-update.component.html',
})
export class CheckAnswerCommandUpdateComponent implements OnInit {
  isSaving = false;
  checkAnswerCommand: ICheckAnswerCommand | null = null;

  evaluatedAnswersSharedCollection: IEvaluatedAnswer[] = [];

  editForm: CheckAnswerCommandFormGroup = this.checkAnswerCommandFormService.createCheckAnswerCommandFormGroup();

  constructor(
    protected checkAnswerCommandService: CheckAnswerCommandService,
    protected checkAnswerCommandFormService: CheckAnswerCommandFormService,
    protected evaluatedAnswerService: EvaluatedAnswerService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareEvaluatedAnswer = (o1: IEvaluatedAnswer | null, o2: IEvaluatedAnswer | null): boolean =>
    this.evaluatedAnswerService.compareEvaluatedAnswer(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ checkAnswerCommand }) => {
      this.checkAnswerCommand = checkAnswerCommand;
      if (checkAnswerCommand) {
        this.updateForm(checkAnswerCommand);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const checkAnswerCommand = this.checkAnswerCommandFormService.getCheckAnswerCommand(this.editForm);
    if (checkAnswerCommand.id !== null) {
      this.subscribeToSaveResponse(this.checkAnswerCommandService.update(checkAnswerCommand));
    } else {
      this.subscribeToSaveResponse(this.checkAnswerCommandService.create(checkAnswerCommand));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICheckAnswerCommand>>): void {
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

  protected updateForm(checkAnswerCommand: ICheckAnswerCommand): void {
    this.checkAnswerCommand = checkAnswerCommand;
    this.checkAnswerCommandFormService.resetForm(this.editForm, checkAnswerCommand);

    this.evaluatedAnswersSharedCollection = this.evaluatedAnswerService.addEvaluatedAnswerToCollectionIfMissing<IEvaluatedAnswer>(
      this.evaluatedAnswersSharedCollection,
      checkAnswerCommand.answer
    );
  }

  protected loadRelationshipsOptions(): void {
    this.evaluatedAnswerService
      .query()
      .pipe(map((res: HttpResponse<IEvaluatedAnswer[]>) => res.body ?? []))
      .pipe(
        map((evaluatedAnswers: IEvaluatedAnswer[]) =>
          this.evaluatedAnswerService.addEvaluatedAnswerToCollectionIfMissing<IEvaluatedAnswer>(
            evaluatedAnswers,
            this.checkAnswerCommand?.answer
          )
        )
      )
      .subscribe((evaluatedAnswers: IEvaluatedAnswer[]) => (this.evaluatedAnswersSharedCollection = evaluatedAnswers));
  }
}
