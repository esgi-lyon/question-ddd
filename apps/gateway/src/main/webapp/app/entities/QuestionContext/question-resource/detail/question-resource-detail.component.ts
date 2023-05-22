import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQuestionResource } from '../question-resource.model';

@Component({
  selector: 'jhi-question-resource-detail',
  templateUrl: './question-resource-detail.component.html',
})
export class QuestionResourceDetailComponent implements OnInit {
  questionResource: IQuestionResource | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ questionResource }) => {
      this.questionResource = questionResource;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
