import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICreateResourceCommand } from '../create-resource-command.model';

@Component({
  selector: 'jhi-create-resource-command-detail',
  templateUrl: './create-resource-command-detail.component.html',
})
export class CreateResourceCommandDetailComponent implements OnInit {
  createResourceCommand: ICreateResourceCommand | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ createResourceCommand }) => {
      this.createResourceCommand = createResourceCommand;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
