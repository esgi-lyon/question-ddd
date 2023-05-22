import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQuestionId } from '../question-id.model';

@Component({
  selector: 'jhi-question-id-detail',
  templateUrl: './question-id-detail.component.html',
})
export class QuestionIdDetailComponent implements OnInit {
  questionId: IQuestionId | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ questionId }) => {
      this.questionId = questionId;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
