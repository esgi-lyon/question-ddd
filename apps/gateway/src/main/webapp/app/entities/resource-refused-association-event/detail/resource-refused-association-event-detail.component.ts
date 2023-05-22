import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResourceRefusedAssociationEvent } from '../resource-refused-association-event.model';

@Component({
  selector: 'jhi-resource-refused-association-event-detail',
  templateUrl: './resource-refused-association-event-detail.component.html',
})
export class ResourceRefusedAssociationEventDetailComponent implements OnInit {
  resourceRefusedAssociationEvent: IResourceRefusedAssociationEvent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ resourceRefusedAssociationEvent }) => {
      this.resourceRefusedAssociationEvent = resourceRefusedAssociationEvent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
