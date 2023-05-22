import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICreateQuestionCommand } from '../create-question-command.model';

@Component({
  selector: 'jhi-create-question-command-detail',
  templateUrl: './create-question-command-detail.component.html',
})
export class CreateQuestionCommandDetailComponent implements OnInit {
  createQuestionCommand: ICreateQuestionCommand | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ createQuestionCommand }) => {
      this.createQuestionCommand = createQuestionCommand;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
