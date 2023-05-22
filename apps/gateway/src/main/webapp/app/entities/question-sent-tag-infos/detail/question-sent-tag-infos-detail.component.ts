import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQuestionSentTagInfos } from '../question-sent-tag-infos.model';

@Component({
  selector: 'jhi-question-sent-tag-infos-detail',
  templateUrl: './question-sent-tag-infos-detail.component.html',
})
export class QuestionSentTagInfosDetailComponent implements OnInit {
  questionSentTagInfos: IQuestionSentTagInfos | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ questionSentTagInfos }) => {
      this.questionSentTagInfos = questionSentTagInfos;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
