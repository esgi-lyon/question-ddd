import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICreateTagCommand } from '../create-tag-command.model';

@Component({
  selector: 'jhi-create-tag-command-detail',
  templateUrl: './create-tag-command-detail.component.html',
})
export class CreateTagCommandDetailComponent implements OnInit {
  createTagCommand: ICreateTagCommand | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ createTagCommand }) => {
      this.createTagCommand = createTagCommand;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
