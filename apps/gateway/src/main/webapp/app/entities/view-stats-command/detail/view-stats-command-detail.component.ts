import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IViewStatsCommand } from '../view-stats-command.model';

@Component({
  selector: 'jhi-view-stats-command-detail',
  templateUrl: './view-stats-command-detail.component.html',
})
export class ViewStatsCommandDetailComponent implements OnInit {
  viewStatsCommand: IViewStatsCommand | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ viewStatsCommand }) => {
      this.viewStatsCommand = viewStatsCommand;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
