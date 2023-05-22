import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { CreateQuestionCommandFormService, CreateQuestionCommandFormGroup } from './create-question-command-form.service';
import { ICreateQuestionCommand } from '../create-question-command.model';
import { CreateQuestionCommandService } from '../service/create-question-command.service';
import { IQuestionSentQuestionResourceTagId } from 'app/entities/question-sent-question-resource-tag-id/question-sent-question-resource-tag-id.model';
import { QuestionSentQuestionResourceTagIdService } from 'app/entities/question-sent-question-resource-tag-id/service/question-sent-question-resource-tag-id.service';

@Component({
  selector: 'jhi-create-question-command-update',
  templateUrl: './create-question-command-update.component.html',
})
export class CreateQuestionCommandUpdateComponent implements OnInit {
  isSaving = false;
  createQuestionCommand: ICreateQuestionCommand | null = null;

  questionSentQuestionResourceTagIdsSharedCollection: IQuestionSentQuestionResourceTagId[] = [];

  editForm: CreateQuestionCommandFormGroup = this.createQuestionCommandFormService.createCreateQuestionCommandFormGroup();

  constructor(
    protected createQuestionCommandService: CreateQuestionCommandService,
    protected createQuestionCommandFormService: CreateQuestionCommandFormService,
    protected questionSentQuestionResourceTagIdService: QuestionSentQuestionResourceTagIdService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareQuestionSentQuestionResourceTagId = (
    o1: IQuestionSentQuestionResourceTagId | null,
    o2: IQuestionSentQuestionResourceTagId | null
  ): boolean => this.questionSentQuestionResourceTagIdService.compareQuestionSentQuestionResourceTagId(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ createQuestionCommand }) => {
      this.createQuestionCommand = createQuestionCommand;
      if (createQuestionCommand) {
        this.updateForm(createQuestionCommand);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const createQuestionCommand = this.createQuestionCommandFormService.getCreateQuestionCommand(this.editForm);
    if (createQuestionCommand.id !== null) {
      this.subscribeToSaveResponse(this.createQuestionCommandService.update(createQuestionCommand));
    } else {
      this.subscribeToSaveResponse(this.createQuestionCommandService.create(createQuestionCommand));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICreateQuestionCommand>>): void {
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

  protected updateForm(createQuestionCommand: ICreateQuestionCommand): void {
    this.createQuestionCommand = createQuestionCommand;
    this.createQuestionCommandFormService.resetForm(this.editForm, createQuestionCommand);

    this.questionSentQuestionResourceTagIdsSharedCollection =
      this.questionSentQuestionResourceTagIdService.addQuestionSentQuestionResourceTagIdToCollectionIfMissing<IQuestionSentQuestionResourceTagId>(
        this.questionSentQuestionResourceTagIdsSharedCollection,
        createQuestionCommand.resource
      );
  }

  protected loadRelationshipsOptions(): void {
    this.questionSentQuestionResourceTagIdService
      .query()
      .pipe(map((res: HttpResponse<IQuestionSentQuestionResourceTagId[]>) => res.body ?? []))
      .pipe(
        map((questionSentQuestionResourceTagIds: IQuestionSentQuestionResourceTagId[]) =>
          this.questionSentQuestionResourceTagIdService.addQuestionSentQuestionResourceTagIdToCollectionIfMissing<IQuestionSentQuestionResourceTagId>(
            questionSentQuestionResourceTagIds,
            this.createQuestionCommand?.resource
          )
        )
      )
      .subscribe(
        (questionSentQuestionResourceTagIds: IQuestionSentQuestionResourceTagId[]) =>
          (this.questionSentQuestionResourceTagIdsSharedCollection = questionSentQuestionResourceTagIds)
      );
  }
}
