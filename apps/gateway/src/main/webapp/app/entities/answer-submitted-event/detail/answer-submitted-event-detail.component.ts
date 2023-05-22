import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAnswerSubmittedEvent } from '../answer-submitted-event.model';

@Component({
  selector: 'jhi-answer-submitted-event-detail',
  templateUrl: './answer-submitted-event-detail.component.html',
})
export class AnswerSubmittedEventDetailComponent implements OnInit {
  answerSubmittedEvent: IAnswerSubmittedEvent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ answerSubmittedEvent }) => {
      this.answerSubmittedEvent = answerSubmittedEvent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
