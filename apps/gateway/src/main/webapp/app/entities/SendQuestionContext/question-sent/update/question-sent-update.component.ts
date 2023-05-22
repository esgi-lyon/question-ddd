import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { QuestionSentFormService, QuestionSentFormGroup } from './question-sent-form.service';
import { IQuestionSent } from '../question-sent.model';
import { QuestionSentService } from '../service/question-sent.service';
import { QuestionNotificationStatus } from 'app/entities/enumerations/question-notification-status.model';

@Component({
  selector: 'jhi-question-sent-update',
  templateUrl: './question-sent-update.component.html',
})
export class QuestionSentUpdateComponent implements OnInit {
  isSaving = false;
  questionSent: IQuestionSent | null = null;
  questionNotificationStatusValues = Object.keys(QuestionNotificationStatus);

  editForm: QuestionSentFormGroup = this.questionSentFormService.createQuestionSentFormGroup();

  constructor(
    protected questionSentService: QuestionSentService,
    protected questionSentFormService: QuestionSentFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ questionSent }) => {
      this.questionSent = questionSent;
      if (questionSent) {
        this.updateForm(questionSent);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const questionSent = this.questionSentFormService.getQuestionSent(this.editForm);
    if (questionSent.id !== null) {
      this.subscribeToSaveResponse(this.questionSentService.update(questionSent));
    } else {
      this.subscribeToSaveResponse(this.questionSentService.create(questionSent));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuestionSent>>): void {
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

  protected updateForm(questionSent: IQuestionSent): void {
    this.questionSent = questionSent;
    this.questionSentFormService.resetForm(this.editForm, questionSent);
  }
}
