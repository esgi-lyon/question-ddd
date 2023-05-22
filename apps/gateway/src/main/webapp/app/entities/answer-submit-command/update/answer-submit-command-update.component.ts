import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { AnswerSubmitCommandFormService, AnswerSubmitCommandFormGroup } from './answer-submit-command-form.service';
import { IAnswerSubmitCommand } from '../answer-submit-command.model';
import { AnswerSubmitCommandService } from '../service/answer-submit-command.service';
import { IAnswer } from 'app/entities/AnswerContext/answer/answer.model';
import { AnswerService } from 'app/entities/AnswerContext/answer/service/answer.service';

@Component({
  selector: 'jhi-answer-submit-command-update',
  templateUrl: './answer-submit-command-update.component.html',
})
export class AnswerSubmitCommandUpdateComponent implements OnInit {
  isSaving = false;
  answerSubmitCommand: IAnswerSubmitCommand | null = null;

  answersSharedCollection: IAnswer[] = [];

  editForm: AnswerSubmitCommandFormGroup = this.answerSubmitCommandFormService.createAnswerSubmitCommandFormGroup();

  constructor(
    protected answerSubmitCommandService: AnswerSubmitCommandService,
    protected answerSubmitCommandFormService: AnswerSubmitCommandFormService,
    protected answerService: AnswerService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareAnswer = (o1: IAnswer | null, o2: IAnswer | null): boolean => this.answerService.compareAnswer(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ answerSubmitCommand }) => {
      this.answerSubmitCommand = answerSubmitCommand;
      if (answerSubmitCommand) {
        this.updateForm(answerSubmitCommand);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const answerSubmitCommand = this.answerSubmitCommandFormService.getAnswerSubmitCommand(this.editForm);
    if (answerSubmitCommand.id !== null) {
      this.subscribeToSaveResponse(this.answerSubmitCommandService.update(answerSubmitCommand));
    } else {
      this.subscribeToSaveResponse(this.answerSubmitCommandService.create(answerSubmitCommand));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAnswerSubmitCommand>>): void {
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

  protected updateForm(answerSubmitCommand: IAnswerSubmitCommand): void {
    this.answerSubmitCommand = answerSubmitCommand;
    this.answerSubmitCommandFormService.resetForm(this.editForm, answerSubmitCommand);

    this.answersSharedCollection = this.answerService.addAnswerToCollectionIfMissing<IAnswer>(
      this.answersSharedCollection,
      answerSubmitCommand.answer
    );
  }

  protected loadRelationshipsOptions(): void {
    this.answerService
      .query()
      .pipe(map((res: HttpResponse<IAnswer[]>) => res.body ?? []))
      .pipe(
        map((answers: IAnswer[]) => this.answerService.addAnswerToCollectionIfMissing<IAnswer>(answers, this.answerSubmitCommand?.answer))
      )
      .subscribe((answers: IAnswer[]) => (this.answersSharedCollection = answers));
  }
}
