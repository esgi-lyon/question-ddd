import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { CreateEvaluationCommandFormService, CreateEvaluationCommandFormGroup } from './create-evaluation-command-form.service';
import { ICreateEvaluationCommand } from '../create-evaluation-command.model';
import { CreateEvaluationCommandService } from '../service/create-evaluation-command.service';
import { IEvaluatedAnswer } from 'app/entities/evaluated-answer/evaluated-answer.model';
import { EvaluatedAnswerService } from 'app/entities/evaluated-answer/service/evaluated-answer.service';

@Component({
  selector: 'jhi-create-evaluation-command-update',
  templateUrl: './create-evaluation-command-update.component.html',
})
export class CreateEvaluationCommandUpdateComponent implements OnInit {
  isSaving = false;
  createEvaluationCommand: ICreateEvaluationCommand | null = null;

  evaluatedAnswersSharedCollection: IEvaluatedAnswer[] = [];

  editForm: CreateEvaluationCommandFormGroup = this.createEvaluationCommandFormService.createCreateEvaluationCommandFormGroup();

  constructor(
    protected createEvaluationCommandService: CreateEvaluationCommandService,
    protected createEvaluationCommandFormService: CreateEvaluationCommandFormService,
    protected evaluatedAnswerService: EvaluatedAnswerService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareEvaluatedAnswer = (o1: IEvaluatedAnswer | null, o2: IEvaluatedAnswer | null): boolean =>
    this.evaluatedAnswerService.compareEvaluatedAnswer(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ createEvaluationCommand }) => {
      this.createEvaluationCommand = createEvaluationCommand;
      if (createEvaluationCommand) {
        this.updateForm(createEvaluationCommand);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const createEvaluationCommand = this.createEvaluationCommandFormService.getCreateEvaluationCommand(this.editForm);
    if (createEvaluationCommand.id !== null) {
      this.subscribeToSaveResponse(this.createEvaluationCommandService.update(createEvaluationCommand));
    } else {
      this.subscribeToSaveResponse(this.createEvaluationCommandService.create(createEvaluationCommand));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICreateEvaluationCommand>>): void {
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

  protected updateForm(createEvaluationCommand: ICreateEvaluationCommand): void {
    this.createEvaluationCommand = createEvaluationCommand;
    this.createEvaluationCommandFormService.resetForm(this.editForm, createEvaluationCommand);

    this.evaluatedAnswersSharedCollection = this.evaluatedAnswerService.addEvaluatedAnswerToCollectionIfMissing<IEvaluatedAnswer>(
      this.evaluatedAnswersSharedCollection,
      createEvaluationCommand.answer
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
            this.createEvaluationCommand?.answer
          )
        )
      )
      .subscribe((evaluatedAnswers: IEvaluatedAnswer[]) => (this.evaluatedAnswersSharedCollection = evaluatedAnswers));
  }
}
