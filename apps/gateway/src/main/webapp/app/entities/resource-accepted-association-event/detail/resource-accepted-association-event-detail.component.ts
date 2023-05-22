import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResourceAcceptedAssociationEvent } from '../resource-accepted-association-event.model';

@Component({
  selector: 'jhi-resource-accepted-association-event-detail',
  templateUrl: './resource-accepted-association-event-detail.component.html',
})
export class ResourceAcceptedAssociationEventDetailComponent implements OnInit {
  resourceAcceptedAssociationEvent: IResourceAcceptedAssociationEvent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ resourceAcceptedAssociationEvent }) => {
      this.resourceAcceptedAssociationEvent = resourceAcceptedAssociationEvent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
