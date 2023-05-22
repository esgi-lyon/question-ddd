import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import {
  SendQuestionByTagsPreferencesCommandFormService,
  SendQuestionByTagsPreferencesCommandFormGroup,
} from './send-question-by-tags-preferences-command-form.service';
import { ISendQuestionByTagsPreferencesCommand } from '../send-question-by-tags-preferences-command.model';
import { SendQuestionByTagsPreferencesCommandService } from '../service/send-question-by-tags-preferences-command.service';
import { IQuestionSent } from 'app/entities/SendQuestionContext/question-sent/question-sent.model';
import { QuestionSentService } from 'app/entities/SendQuestionContext/question-sent/service/question-sent.service';

@Component({
  selector: 'jhi-send-question-by-tags-preferences-command-update',
  templateUrl: './send-question-by-tags-preferences-command-update.component.html',
})
export class SendQuestionByTagsPreferencesCommandUpdateComponent implements OnInit {
  isSaving = false;
  sendQuestionByTagsPreferencesCommand: ISendQuestionByTagsPreferencesCommand | null = null;

  questionSentsSharedCollection: IQuestionSent[] = [];

  editForm: SendQuestionByTagsPreferencesCommandFormGroup =
    this.sendQuestionByTagsPreferencesCommandFormService.createSendQuestionByTagsPreferencesCommandFormGroup();

  constructor(
    protected sendQuestionByTagsPreferencesCommandService: SendQuestionByTagsPreferencesCommandService,
    protected sendQuestionByTagsPreferencesCommandFormService: SendQuestionByTagsPreferencesCommandFormService,
    protected questionSentService: QuestionSentService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareQuestionSent = (o1: IQuestionSent | null, o2: IQuestionSent | null): boolean =>
    this.questionSentService.compareQuestionSent(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sendQuestionByTagsPreferencesCommand }) => {
      this.sendQuestionByTagsPreferencesCommand = sendQuestionByTagsPreferencesCommand;
      if (sendQuestionByTagsPreferencesCommand) {
        this.updateForm(sendQuestionByTagsPreferencesCommand);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sendQuestionByTagsPreferencesCommand =
      this.sendQuestionByTagsPreferencesCommandFormService.getSendQuestionByTagsPreferencesCommand(this.editForm);
    if (sendQuestionByTagsPreferencesCommand.id !== null) {
      this.subscribeToSaveResponse(this.sendQuestionByTagsPreferencesCommandService.update(sendQuestionByTagsPreferencesCommand));
    } else {
      this.subscribeToSaveResponse(this.sendQuestionByTagsPreferencesCommandService.create(sendQuestionByTagsPreferencesCommand));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISendQuestionByTagsPreferencesCommand>>): void {
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

  protected updateForm(sendQuestionByTagsPreferencesCommand: ISendQuestionByTagsPreferencesCommand): void {
    this.sendQuestionByTagsPreferencesCommand = sendQuestionByTagsPreferencesCommand;
    this.sendQuestionByTagsPreferencesCommandFormService.resetForm(this.editForm, sendQuestionByTagsPreferencesCommand);

    this.questionSentsSharedCollection = this.questionSentService.addQuestionSentToCollectionIfMissing<IQuestionSent>(
      this.questionSentsSharedCollection,
      sendQuestionByTagsPreferencesCommand.questionSent
    );
  }

  protected loadRelationshipsOptions(): void {
    this.questionSentService
      .query()
      .pipe(map((res: HttpResponse<IQuestionSent[]>) => res.body ?? []))
      .pipe(
        map((questionSents: IQuestionSent[]) =>
          this.questionSentService.addQuestionSentToCollectionIfMissing<IQuestionSent>(
            questionSents,
            this.sendQuestionByTagsPreferencesCommand?.questionSent
          )
        )
      )
      .subscribe((questionSents: IQuestionSent[]) => (this.questionSentsSharedCollection = questionSents));
  }
}
