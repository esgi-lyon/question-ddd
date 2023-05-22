import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStatisticSubjectQuestion } from '../statistic-subject-question.model';

@Component({
  selector: 'jhi-statistic-subject-question-detail',
  templateUrl: './statistic-subject-question-detail.component.html',
})
export class StatisticSubjectQuestionDetailComponent implements OnInit {
  statisticSubjectQuestion: IStatisticSubjectQuestion | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ statisticSubjectQuestion }) => {
      this.statisticSubjectQuestion = statisticSubjectQuestion;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
