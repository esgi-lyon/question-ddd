import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserStatsViewedEvent } from '../user-stats-viewed-event.model';

@Component({
  selector: 'jhi-user-stats-viewed-event-detail',
  templateUrl: './user-stats-viewed-event-detail.component.html',
})
export class UserStatsViewedEventDetailComponent implements OnInit {
  userStatsViewedEvent: IUserStatsViewedEvent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userStatsViewedEvent }) => {
      this.userStatsViewedEvent = userStatsViewedEvent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
