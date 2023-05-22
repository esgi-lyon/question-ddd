import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStatisticSubjectTag } from '../statistic-subject-tag.model';

@Component({
  selector: 'jhi-statistic-subject-tag-detail',
  templateUrl: './statistic-subject-tag-detail.component.html',
})
export class StatisticSubjectTagDetailComponent implements OnInit {
  statisticSubjectTag: IStatisticSubjectTag | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ statisticSubjectTag }) => {
      this.statisticSubjectTag = statisticSubjectTag;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
