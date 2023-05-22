import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEvaluationQuestion } from '../evaluation-question.model';

@Component({
  selector: 'jhi-evaluation-question-detail',
  templateUrl: './evaluation-question-detail.component.html',
})
export class EvaluationQuestionDetailComponent implements OnInit {
  evaluationQuestion: IEvaluationQuestion | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ evaluationQuestion }) => {
      this.evaluationQuestion = evaluationQuestion;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
