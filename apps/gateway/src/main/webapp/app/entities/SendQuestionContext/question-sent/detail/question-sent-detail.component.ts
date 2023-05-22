import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQuestionSent } from '../question-sent.model';

@Component({
  selector: 'jhi-question-sent-detail',
  templateUrl: './question-sent-detail.component.html',
})
export class QuestionSentDetailComponent implements OnInit {
  questionSent: IQuestionSent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ questionSent }) => {
      this.questionSent = questionSent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
