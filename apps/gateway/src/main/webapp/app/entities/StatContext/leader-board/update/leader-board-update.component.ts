import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { LeaderBoardFormService, LeaderBoardFormGroup } from './leader-board-form.service';
import { ILeaderBoard } from '../leader-board.model';
import { LeaderBoardService } from '../service/leader-board.service';
import { IStatisticSubjectTag } from 'app/entities/statistic-subject-tag/statistic-subject-tag.model';
import { StatisticSubjectTagService } from 'app/entities/statistic-subject-tag/service/statistic-subject-tag.service';
import { DifficultyLevel } from 'app/entities/enumerations/difficulty-level.model';

@Component({
  selector: 'jhi-leader-board-update',
  templateUrl: './leader-board-update.component.html',
})
export class LeaderBoardUpdateComponent implements OnInit {
  isSaving = false;
  leaderBoard: ILeaderBoard | null = null;
  difficultyLevelValues = Object.keys(DifficultyLevel);

  statisticSubjectTagsSharedCollection: IStatisticSubjectTag[] = [];

  editForm: LeaderBoardFormGroup = this.leaderBoardFormService.createLeaderBoardFormGroup();

  constructor(
    protected leaderBoardService: LeaderBoardService,
    protected leaderBoardFormService: LeaderBoardFormService,
    protected statisticSubjectTagService: StatisticSubjectTagService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareStatisticSubjectTag = (o1: IStatisticSubjectTag | null, o2: IStatisticSubjectTag | null): boolean =>
    this.statisticSubjectTagService.compareStatisticSubjectTag(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ leaderBoard }) => {
      this.leaderBoard = leaderBoard;
      if (leaderBoard) {
        this.updateForm(leaderBoard);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const leaderBoard = this.leaderBoardFormService.getLeaderBoard(this.editForm);
    if (leaderBoard.id !== null) {
      this.subscribeToSaveResponse(this.leaderBoardService.update(leaderBoard));
    } else {
      this.subscribeToSaveResponse(this.leaderBoardService.create(leaderBoard));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILeaderBoard>>): void {
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

  protected updateForm(leaderBoard: ILeaderBoard): void {
    this.leaderBoard = leaderBoard;
    this.leaderBoardFormService.resetForm(this.editForm, leaderBoard);

    this.statisticSubjectTagsSharedCollection =
      this.statisticSubjectTagService.addStatisticSubjectTagToCollectionIfMissing<IStatisticSubjectTag>(
        this.statisticSubjectTagsSharedCollection,
        leaderBoard.tagId
      );
  }

  protected loadRelationshipsOptions(): void {
    this.statisticSubjectTagService
      .query()
      .pipe(map((res: HttpResponse<IStatisticSubjectTag[]>) => res.body ?? []))
      .pipe(
        map((statisticSubjectTags: IStatisticSubjectTag[]) =>
          this.statisticSubjectTagService.addStatisticSubjectTagToCollectionIfMissing<IStatisticSubjectTag>(
            statisticSubjectTags,
            this.leaderBoard?.tagId
          )
        )
      )
      .subscribe((statisticSubjectTags: IStatisticSubjectTag[]) => (this.statisticSubjectTagsSharedCollection = statisticSubjectTags));
  }
}
