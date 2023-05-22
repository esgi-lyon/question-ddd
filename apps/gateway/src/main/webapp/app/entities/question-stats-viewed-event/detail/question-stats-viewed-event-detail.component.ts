import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQuestionStatsViewedEvent } from '../question-stats-viewed-event.model';

@Component({
  selector: 'jhi-question-stats-viewed-event-detail',
  templateUrl: './question-stats-viewed-event-detail.component.html',
})
export class QuestionStatsViewedEventDetailComponent implements OnInit {
  questionStatsViewedEvent: IQuestionStatsViewedEvent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ questionStatsViewedEvent }) => {
      this.questionStatsViewedEvent = questionStatsViewedEvent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
