import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAnswerCheckedEvent } from '../answer-checked-event.model';

@Component({
  selector: 'jhi-answer-checked-event-detail',
  templateUrl: './answer-checked-event-detail.component.html',
})
export class AnswerCheckedEventDetailComponent implements OnInit {
  answerCheckedEvent: IAnswerCheckedEvent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ answerCheckedEvent }) => {
      this.answerCheckedEvent = answerCheckedEvent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
