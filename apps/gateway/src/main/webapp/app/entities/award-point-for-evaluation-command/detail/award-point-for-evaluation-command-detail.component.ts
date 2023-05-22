import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAwardPointForEvaluationCommand } from '../award-point-for-evaluation-command.model';

@Component({
  selector: 'jhi-award-point-for-evaluation-command-detail',
  templateUrl: './award-point-for-evaluation-command-detail.component.html',
})
export class AwardPointForEvaluationCommandDetailComponent implements OnInit {
  awardPointForEvaluationCommand: IAwardPointForEvaluationCommand | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ awardPointForEvaluationCommand }) => {
      this.awardPointForEvaluationCommand = awardPointForEvaluationCommand;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
