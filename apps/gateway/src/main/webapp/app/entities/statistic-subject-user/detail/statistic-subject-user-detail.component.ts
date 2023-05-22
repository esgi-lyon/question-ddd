import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStatisticSubjectUser } from '../statistic-subject-user.model';

@Component({
  selector: 'jhi-statistic-subject-user-detail',
  templateUrl: './statistic-subject-user-detail.component.html',
})
export class StatisticSubjectUserDetailComponent implements OnInit {
  statisticSubjectUser: IStatisticSubjectUser | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ statisticSubjectUser }) => {
      this.statisticSubjectUser = statisticSubjectUser;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
