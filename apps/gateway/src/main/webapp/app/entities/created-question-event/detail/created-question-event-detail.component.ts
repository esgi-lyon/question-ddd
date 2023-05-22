import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICreatedQuestionEvent } from '../created-question-event.model';

@Component({
  selector: 'jhi-created-question-event-detail',
  templateUrl: './created-question-event-detail.component.html',
})
export class CreatedQuestionEventDetailComponent implements OnInit {
  createdQuestionEvent: ICreatedQuestionEvent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ createdQuestionEvent }) => {
      this.createdQuestionEvent = createdQuestionEvent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
