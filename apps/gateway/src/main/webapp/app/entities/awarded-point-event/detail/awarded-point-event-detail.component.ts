import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAwardedPointEvent } from '../awarded-point-event.model';

@Component({
  selector: 'jhi-awarded-point-event-detail',
  templateUrl: './awarded-point-event-detail.component.html',
})
export class AwardedPointEventDetailComponent implements OnInit {
  awardedPointEvent: IAwardedPointEvent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ awardedPointEvent }) => {
      this.awardedPointEvent = awardedPointEvent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
