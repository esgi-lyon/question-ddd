import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITagChoicesListedEvent } from '../tag-choices-listed-event.model';

@Component({
  selector: 'jhi-tag-choices-listed-event-detail',
  templateUrl: './tag-choices-listed-event-detail.component.html',
})
export class TagChoicesListedEventDetailComponent implements OnInit {
  tagChoicesListedEvent: ITagChoicesListedEvent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tagChoicesListedEvent }) => {
      this.tagChoicesListedEvent = tagChoicesListedEvent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
