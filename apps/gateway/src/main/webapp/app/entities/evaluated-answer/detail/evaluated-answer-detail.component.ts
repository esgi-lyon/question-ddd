import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEvaluatedAnswer } from '../evaluated-answer.model';

@Component({
  selector: 'jhi-evaluated-answer-detail',
  templateUrl: './evaluated-answer-detail.component.html',
})
export class EvaluatedAnswerDetailComponent implements OnInit {
  evaluatedAnswer: IEvaluatedAnswer | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ evaluatedAnswer }) => {
      this.evaluatedAnswer = evaluatedAnswer;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
