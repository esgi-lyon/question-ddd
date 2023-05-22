import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAnswerSubmitCommand } from '../answer-submit-command.model';

@Component({
  selector: 'jhi-answer-submit-command-detail',
  templateUrl: './answer-submit-command-detail.component.html',
})
export class AnswerSubmitCommandDetailComponent implements OnInit {
  answerSubmitCommand: IAnswerSubmitCommand | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ answerSubmitCommand }) => {
      this.answerSubmitCommand = answerSubmitCommand;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
