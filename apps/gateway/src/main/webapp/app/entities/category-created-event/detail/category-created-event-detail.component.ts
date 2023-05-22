import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICategoryCreatedEvent } from '../category-created-event.model';

@Component({
  selector: 'jhi-category-created-event-detail',
  templateUrl: './category-created-event-detail.component.html',
})
export class CategoryCreatedEventDetailComponent implements OnInit {
  categoryCreatedEvent: ICategoryCreatedEvent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ categoryCreatedEvent }) => {
      this.categoryCreatedEvent = categoryCreatedEvent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
