import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQuestionResourceTagInfos } from '../question-resource-tag-infos.model';

@Component({
  selector: 'jhi-question-resource-tag-infos-detail',
  templateUrl: './question-resource-tag-infos-detail.component.html',
})
export class QuestionResourceTagInfosDetailComponent implements OnInit {
  questionResourceTagInfos: IQuestionResourceTagInfos | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ questionResourceTagInfos }) => {
      this.questionResourceTagInfos = questionResourceTagInfos;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
