import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INotifiedQuestionEvent } from '../notified-question-event.model';

@Component({
  selector: 'jhi-notified-question-event-detail',
  templateUrl: './notified-question-event-detail.component.html',
})
export class NotifiedQuestionEventDetailComponent implements OnInit {
  notifiedQuestionEvent: INotifiedQuestionEvent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ notifiedQuestionEvent }) => {
      this.notifiedQuestionEvent = notifiedQuestionEvent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
