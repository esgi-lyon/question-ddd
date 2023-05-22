import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITagCreatedEvent } from '../tag-created-event.model';

@Component({
  selector: 'jhi-tag-created-event-detail',
  templateUrl: './tag-created-event-detail.component.html',
})
export class TagCreatedEventDetailComponent implements OnInit {
  tagCreatedEvent: ITagCreatedEvent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tagCreatedEvent }) => {
      this.tagCreatedEvent = tagCreatedEvent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
