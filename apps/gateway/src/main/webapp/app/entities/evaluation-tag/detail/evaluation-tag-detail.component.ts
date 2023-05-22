import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEvaluationTag } from '../evaluation-tag.model';

@Component({
  selector: 'jhi-evaluation-tag-detail',
  templateUrl: './evaluation-tag-detail.component.html',
})
export class EvaluationTagDetailComponent implements OnInit {
  evaluationTag: IEvaluationTag | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ evaluationTag }) => {
      this.evaluationTag = evaluationTag;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
