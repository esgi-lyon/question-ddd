import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { EvaluationFormService, EvaluationFormGroup } from './evaluation-form.service';
import { IEvaluation } from '../evaluation.model';
import { EvaluationService } from '../service/evaluation.service';
import { IEvaluationTag } from 'app/entities/evaluation-tag/evaluation-tag.model';
import { EvaluationTagService } from 'app/entities/evaluation-tag/service/evaluation-tag.service';
import { IEvaluationQuestion } from 'app/entities/evaluation-question/evaluation-question.model';
import { EvaluationQuestionService } from 'app/entities/evaluation-question/service/evaluation-question.service';
import { IAnsweringUser } from 'app/entities/answering-user/answering-user.model';
import { AnsweringUserService } from 'app/entities/answering-user/service/answering-user.service';
import { Status } from 'app/entities/enumerations/status.model';
import { DifficultyLevel } from 'app/entities/enumerations/difficulty-level.model';

@Component({
  selector: 'jhi-evaluation-update',
  templateUrl: './evaluation-update.component.html',
})
export class EvaluationUpdateComponent implements OnInit {
  isSaving = false;
  evaluation: IEvaluation | null = null;
  statusValues = Object.keys(Status);
  difficultyLevelValues = Object.keys(DifficultyLevel);

  evaluationTagsSharedCollection: IEvaluationTag[] = [];
  evaluationQuestionsSharedCollection: IEvaluationQuestion[] = [];
  answeringUsersSharedCollection: IAnsweringUser[] = [];

  editForm: EvaluationFormGroup = this.evaluationFormService.createEvaluationFormGroup();

  constructor(
    protected evaluationService: EvaluationService,
    protected evaluationFormService: EvaluationFormService,
    protected evaluationTagService: EvaluationTagService,
    protected evaluationQuestionService: EvaluationQuestionService,
    protected answeringUserService: AnsweringUserService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareEvaluationTag = (o1: IEvaluationTag | null, o2: IEvaluationTag | null): boolean =>
    this.evaluationTagService.compareEvaluationTag(o1, o2);

  compareEvaluationQuestion = (o1: IEvaluationQuestion | null, o2: IEvaluationQuestion | null): boolean =>
    this.evaluationQuestionService.compareEvaluationQuestion(o1, o2);

  compareAnsweringUser = (o1: IAnsweringUser | null, o2: IAnsweringUser | null): boolean =>
    this.answeringUserService.compareAnsweringUser(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ evaluation }) => {
      this.evaluation = evaluation;
      if (evaluation) {
        this.updateForm(evaluation);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const evaluation = this.evaluationFormService.getEvaluation(this.editForm);
    if (evaluation.id !== null) {
      this.subscribeToSaveResponse(this.evaluationService.update(evaluation));
    } else {
      this.subscribeToSaveResponse(this.evaluationService.create(evaluation));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEvaluation>>): void {
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

  protected updateForm(evaluation: IEvaluation): void {
    this.evaluation = evaluation;
    this.evaluationFormService.resetForm(this.editForm, evaluation);

    this.evaluationTagsSharedCollection = this.evaluationTagService.addEvaluationTagToCollectionIfMissing<IEvaluationTag>(
      this.evaluationTagsSharedCollection,
      evaluation.tag
    );
    this.evaluationQuestionsSharedCollection =
      this.evaluationQuestionService.addEvaluationQuestionToCollectionIfMissing<IEvaluationQuestion>(
        this.evaluationQuestionsSharedCollection,
        evaluation.question
      );
    this.answeringUsersSharedCollection = this.answeringUserService.addAnsweringUserToCollectionIfMissing<IAnsweringUser>(
      this.answeringUsersSharedCollection,
      evaluation.user
    );
  }

  protected loadRelationshipsOptions(): void {
    this.evaluationTagService
      .query()
      .pipe(map((res: HttpResponse<IEvaluationTag[]>) => res.body ?? []))
      .pipe(
        map((evaluationTags: IEvaluationTag[]) =>
          this.evaluationTagService.addEvaluationTagToCollectionIfMissing<IEvaluationTag>(evaluationTags, this.evaluation?.tag)
        )
      )
      .subscribe((evaluationTags: IEvaluationTag[]) => (this.evaluationTagsSharedCollection = evaluationTags));

    this.evaluationQuestionService
      .query()
      .pipe(map((res: HttpResponse<IEvaluationQuestion[]>) => res.body ?? []))
      .pipe(
        map((evaluationQuestions: IEvaluationQuestion[]) =>
          this.evaluationQuestionService.addEvaluationQuestionToCollectionIfMissing<IEvaluationQuestion>(
            evaluationQuestions,
            this.evaluation?.question
          )
        )
      )
      .subscribe((evaluationQuestions: IEvaluationQuestion[]) => (this.evaluationQuestionsSharedCollection = evaluationQuestions));

    this.answeringUserService
      .query()
      .pipe(map((res: HttpResponse<IAnsweringUser[]>) => res.body ?? []))
      .pipe(
        map((answeringUsers: IAnsweringUser[]) =>
          this.answeringUserService.addAnsweringUserToCollectionIfMissing<IAnsweringUser>(answeringUsers, this.evaluation?.user)
        )
      )
      .subscribe((answeringUsers: IAnsweringUser[]) => (this.answeringUsersSharedCollection = answeringUsers));
  }
}
