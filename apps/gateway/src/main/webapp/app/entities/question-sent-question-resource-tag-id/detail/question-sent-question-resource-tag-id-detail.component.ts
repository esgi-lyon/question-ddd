import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQuestionSentQuestionResourceTagId } from '../question-sent-question-resource-tag-id.model';

@Component({
  selector: 'jhi-question-sent-question-resource-tag-id-detail',
  templateUrl: './question-sent-question-resource-tag-id-detail.component.html',
})
export class QuestionSentQuestionResourceTagIdDetailComponent implements OnInit {
  questionSentQuestionResourceTagId: IQuestionSentQuestionResourceTagId | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ questionSentQuestionResourceTagId }) => {
      this.questionSentQuestionResourceTagId = questionSentQuestionResourceTagId;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
