import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPointAwardRule } from '../point-award-rule.model';

@Component({
  selector: 'jhi-point-award-rule-detail',
  templateUrl: './point-award-rule-detail.component.html',
})
export class PointAwardRuleDetailComponent implements OnInit {
  pointAwardRule: IPointAwardRule | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pointAwardRule }) => {
      this.pointAwardRule = pointAwardRule;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
