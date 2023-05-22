import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEvaluationId } from '../evaluation-id.model';

@Component({
  selector: 'jhi-evaluation-id-detail',
  templateUrl: './evaluation-id-detail.component.html',
})
export class EvaluationIdDetailComponent implements OnInit {
  evaluationId: IEvaluationId | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ evaluationId }) => {
      this.evaluationId = evaluationId;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
