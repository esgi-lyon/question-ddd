import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITagStatsViewedEvent } from '../tag-stats-viewed-event.model';

@Component({
  selector: 'jhi-tag-stats-viewed-event-detail',
  templateUrl: './tag-stats-viewed-event-detail.component.html',
})
export class TagStatsViewedEventDetailComponent implements OnInit {
  tagStatsViewedEvent: ITagStatsViewedEvent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tagStatsViewedEvent }) => {
      this.tagStatsViewedEvent = tagStatsViewedEvent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
