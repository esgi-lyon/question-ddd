import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { AnswerFormService, AnswerFormGroup } from './answer-form.service';
import { IAnswer } from '../answer.model';
import { AnswerService } from '../service/answer.service';
import { IQuestionId } from 'app/entities/question-id/question-id.model';
import { QuestionIdService } from 'app/entities/question-id/service/question-id.service';
import { IAnsweredTag } from 'app/entities/answered-tag/answered-tag.model';
import { AnsweredTagService } from 'app/entities/answered-tag/service/answered-tag.service';

@Component({
  selector: 'jhi-answer-update',
  templateUrl: './answer-update.component.html',
})
export class AnswerUpdateComponent implements OnInit {
  isSaving = false;
  answer: IAnswer | null = null;

  questionIdsSharedCollection: IQuestionId[] = [];
  answeredTagsSharedCollection: IAnsweredTag[] = [];

  editForm: AnswerFormGroup = this.answerFormService.createAnswerFormGroup();

  constructor(
    protected answerService: AnswerService,
    protected answerFormService: AnswerFormService,
    protected questionIdService: QuestionIdService,
    protected answeredTagService: AnsweredTagService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareQuestionId = (o1: IQuestionId | null, o2: IQuestionId | null): boolean => this.questionIdService.compareQuestionId(o1, o2);

  compareAnsweredTag = (o1: IAnsweredTag | null, o2: IAnsweredTag | null): boolean => this.answeredTagService.compareAnsweredTag(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ answer }) => {
      this.answer = answer;
      if (answer) {
        this.updateForm(answer);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const answer = this.answerFormService.getAnswer(this.editForm);
    if (answer.id !== null) {
      this.subscribeToSaveResponse(this.answerService.update(answer));
    } else {
      this.subscribeToSaveResponse(this.answerService.create(answer));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAnswer>>): void {
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

  protected updateForm(answer: IAnswer): void {
    this.answer = answer;
    this.answerFormService.resetForm(this.editForm, answer);

    this.questionIdsSharedCollection = this.questionIdService.addQuestionIdToCollectionIfMissing<IQuestionId>(
      this.questionIdsSharedCollection,
      answer.question
    );
    this.answeredTagsSharedCollection = this.answeredTagService.addAnsweredTagToCollectionIfMissing<IAnsweredTag>(
      this.answeredTagsSharedCollection,
      answer.answeredTag
    );
  }

  protected loadRelationshipsOptions(): void {
    this.questionIdService
      .query()
      .pipe(map((res: HttpResponse<IQuestionId[]>) => res.body ?? []))
      .pipe(
        map((questionIds: IQuestionId[]) =>
          this.questionIdService.addQuestionIdToCollectionIfMissing<IQuestionId>(questionIds, this.answer?.question)
        )
      )
      .subscribe((questionIds: IQuestionId[]) => (this.questionIdsSharedCollection = questionIds));

    this.answeredTagService
      .query()
      .pipe(map((res: HttpResponse<IAnsweredTag[]>) => res.body ?? []))
      .pipe(
        map((answeredTags: IAnsweredTag[]) =>
          this.answeredTagService.addAnsweredTagToCollectionIfMissing<IAnsweredTag>(answeredTags, this.answer?.answeredTag)
        )
      )
      .subscribe((answeredTags: IAnsweredTag[]) => (this.answeredTagsSharedCollection = answeredTags));
  }
}
