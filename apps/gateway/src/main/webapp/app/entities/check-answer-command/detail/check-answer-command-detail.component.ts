import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICheckAnswerCommand } from '../check-answer-command.model';

@Component({
  selector: 'jhi-check-answer-command-detail',
  templateUrl: './check-answer-command-detail.component.html',
})
export class CheckAnswerCommandDetailComponent implements OnInit {
  checkAnswerCommand: ICheckAnswerCommand | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ checkAnswerCommand }) => {
      this.checkAnswerCommand = checkAnswerCommand;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
