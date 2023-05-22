import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEvaluationCreatedEvent } from '../evaluation-created-event.model';

@Component({
  selector: 'jhi-evaluation-created-event-detail',
  templateUrl: './evaluation-created-event-detail.component.html',
})
export class EvaluationCreatedEventDetailComponent implements OnInit {
  evaluationCreatedEvent: IEvaluationCreatedEvent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ evaluationCreatedEvent }) => {
      this.evaluationCreatedEvent = evaluationCreatedEvent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
