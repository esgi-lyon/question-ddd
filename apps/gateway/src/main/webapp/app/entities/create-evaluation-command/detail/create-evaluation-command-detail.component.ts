import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICreateEvaluationCommand } from '../create-evaluation-command.model';

@Component({
  selector: 'jhi-create-evaluation-command-detail',
  templateUrl: './create-evaluation-command-detail.component.html',
})
export class CreateEvaluationCommandDetailComponent implements OnInit {
  createEvaluationCommand: ICreateEvaluationCommand | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ createEvaluationCommand }) => {
      this.createEvaluationCommand = createEvaluationCommand;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
