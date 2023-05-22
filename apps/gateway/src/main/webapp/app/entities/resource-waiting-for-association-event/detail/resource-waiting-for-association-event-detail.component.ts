import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResourceWaitingForAssociationEvent } from '../resource-waiting-for-association-event.model';

@Component({
  selector: 'jhi-resource-waiting-for-association-event-detail',
  templateUrl: './resource-waiting-for-association-event-detail.component.html',
})
export class ResourceWaitingForAssociationEventDetailComponent implements OnInit {
  resourceWaitingForAssociationEvent: IResourceWaitingForAssociationEvent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ resourceWaitingForAssociationEvent }) => {
      this.resourceWaitingForAssociationEvent = resourceWaitingForAssociationEvent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
