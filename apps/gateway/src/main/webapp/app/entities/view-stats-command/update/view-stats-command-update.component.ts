import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { ViewStatsCommandFormService, ViewStatsCommandFormGroup } from './view-stats-command-form.service';
import { IViewStatsCommand } from '../view-stats-command.model';
import { ViewStatsCommandService } from '../service/view-stats-command.service';
import { IStatisticSubjectUser } from 'app/entities/statistic-subject-user/statistic-subject-user.model';
import { StatisticSubjectUserService } from 'app/entities/statistic-subject-user/service/statistic-subject-user.service';
import { IStatisticSubjectQuestion } from 'app/entities/statistic-subject-question/statistic-subject-question.model';
import { StatisticSubjectQuestionService } from 'app/entities/statistic-subject-question/service/statistic-subject-question.service';
import { IStatisticSubjectTag } from 'app/entities/statistic-subject-tag/statistic-subject-tag.model';
import { StatisticSubjectTagService } from 'app/entities/statistic-subject-tag/service/statistic-subject-tag.service';

@Component({
  selector: 'jhi-view-stats-command-update',
  templateUrl: './view-stats-command-update.component.html',
})
export class ViewStatsCommandUpdateComponent implements OnInit {
  isSaving = false;
  viewStatsCommand: IViewStatsCommand | null = null;

  statisticSubjectUsersSharedCollection: IStatisticSubjectUser[] = [];
  statisticSubjectQuestionsSharedCollection: IStatisticSubjectQuestion[] = [];
  statisticSubjectTagsSharedCollection: IStatisticSubjectTag[] = [];

  editForm: ViewStatsCommandFormGroup = this.viewStatsCommandFormService.createViewStatsCommandFormGroup();

  constructor(
    protected viewStatsCommandService: ViewStatsCommandService,
    protected viewStatsCommandFormService: ViewStatsCommandFormService,
    protected statisticSubjectUserService: StatisticSubjectUserService,
    protected statisticSubjectQuestionService: StatisticSubjectQuestionService,
    protected statisticSubjectTagService: StatisticSubjectTagService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareStatisticSubjectUser = (o1: IStatisticSubjectUser | null, o2: IStatisticSubjectUser | null): boolean =>
    this.statisticSubjectUserService.compareStatisticSubjectUser(o1, o2);

  compareStatisticSubjectQuestion = (o1: IStatisticSubjectQuestion | null, o2: IStatisticSubjectQuestion | null): boolean =>
    this.statisticSubjectQuestionService.compareStatisticSubjectQuestion(o1, o2);

  compareStatisticSubjectTag = (o1: IStatisticSubjectTag | null, o2: IStatisticSubjectTag | null): boolean =>
    this.statisticSubjectTagService.compareStatisticSubjectTag(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ viewStatsCommand }) => {
      this.viewStatsCommand = viewStatsCommand;
      if (viewStatsCommand) {
        this.updateForm(viewStatsCommand);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const viewStatsCommand = this.viewStatsCommandFormService.getViewStatsCommand(this.editForm);
    if (viewStatsCommand.id !== null) {
      this.subscribeToSaveResponse(this.viewStatsCommandService.update(viewStatsCommand));
    } else {
      this.subscribeToSaveResponse(this.viewStatsCommandService.create(viewStatsCommand));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IViewStatsCommand>>): void {
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

  protected updateForm(viewStatsCommand: IViewStatsCommand): void {
    this.viewStatsCommand = viewStatsCommand;
    this.viewStatsCommandFormService.resetForm(this.editForm, viewStatsCommand);

    this.statisticSubjectUsersSharedCollection =
      this.statisticSubjectUserService.addStatisticSubjectUserToCollectionIfMissing<IStatisticSubjectUser>(
        this.statisticSubjectUsersSharedCollection,
        viewStatsCommand.user
      );
    this.statisticSubjectQuestionsSharedCollection =
      this.statisticSubjectQuestionService.addStatisticSubjectQuestionToCollectionIfMissing<IStatisticSubjectQuestion>(
        this.statisticSubjectQuestionsSharedCollection,
        viewStatsCommand.question
      );
    this.statisticSubjectTagsSharedCollection =
      this.statisticSubjectTagService.addStatisticSubjectTagToCollectionIfMissing<IStatisticSubjectTag>(
        this.statisticSubjectTagsSharedCollection,
        viewStatsCommand.tag
      );
  }

  protected loadRelationshipsOptions(): void {
    this.statisticSubjectUserService
      .query()
      .pipe(map((res: HttpResponse<IStatisticSubjectUser[]>) => res.body ?? []))
      .pipe(
        map((statisticSubjectUsers: IStatisticSubjectUser[]) =>
          this.statisticSubjectUserService.addStatisticSubjectUserToCollectionIfMissing<IStatisticSubjectUser>(
            statisticSubjectUsers,
            this.viewStatsCommand?.user
          )
        )
      )
      .subscribe((statisticSubjectUsers: IStatisticSubjectUser[]) => (this.statisticSubjectUsersSharedCollection = statisticSubjectUsers));

    this.statisticSubjectQuestionService
      .query()
      .pipe(map((res: HttpResponse<IStatisticSubjectQuestion[]>) => res.body ?? []))
      .pipe(
        map((statisticSubjectQuestions: IStatisticSubjectQuestion[]) =>
          this.statisticSubjectQuestionService.addStatisticSubjectQuestionToCollectionIfMissing<IStatisticSubjectQuestion>(
            statisticSubjectQuestions,
            this.viewStatsCommand?.question
          )
        )
      )
      .subscribe(
        (statisticSubjectQuestions: IStatisticSubjectQuestion[]) =>
          (this.statisticSubjectQuestionsSharedCollection = statisticSubjectQuestions)
      );

    this.statisticSubjectTagService
      .query()
      .pipe(map((res: HttpResponse<IStatisticSubjectTag[]>) => res.body ?? []))
      .pipe(
        map((statisticSubjectTags: IStatisticSubjectTag[]) =>
          this.statisticSubjectTagService.addStatisticSubjectTagToCollectionIfMissing<IStatisticSubjectTag>(
            statisticSubjectTags,
            this.viewStatsCommand?.tag
          )
        )
      )
      .subscribe((statisticSubjectTags: IStatisticSubjectTag[]) => (this.statisticSubjectTagsSharedCollection = statisticSubjectTags));
  }
}
