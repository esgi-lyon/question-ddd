import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRejectResourceTagCommand } from '../reject-resource-tag-command.model';

@Component({
  selector: 'jhi-reject-resource-tag-command-detail',
  templateUrl: './reject-resource-tag-command-detail.component.html',
})
export class RejectResourceTagCommandDetailComponent implements OnInit {
  rejectResourceTagCommand: IRejectResourceTagCommand | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rejectResourceTagCommand }) => {
      this.rejectResourceTagCommand = rejectResourceTagCommand;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
