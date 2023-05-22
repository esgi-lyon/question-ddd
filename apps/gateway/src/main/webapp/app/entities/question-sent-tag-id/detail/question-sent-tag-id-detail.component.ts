import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQuestionSentTagId } from '../question-sent-tag-id.model';

@Component({
  selector: 'jhi-question-sent-tag-id-detail',
  templateUrl: './question-sent-tag-id-detail.component.html',
})
export class QuestionSentTagIdDetailComponent implements OnInit {
  questionSentTagId: IQuestionSentTagId | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ questionSentTagId }) => {
      this.questionSentTagId = questionSentTagId;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
