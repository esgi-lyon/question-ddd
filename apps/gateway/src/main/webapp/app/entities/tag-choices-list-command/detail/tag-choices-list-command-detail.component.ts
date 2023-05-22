import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITagChoicesListCommand } from '../tag-choices-list-command.model';

@Component({
  selector: 'jhi-tag-choices-list-command-detail',
  templateUrl: './tag-choices-list-command-detail.component.html',
})
export class TagChoicesListCommandDetailComponent implements OnInit {
  tagChoicesListCommand: ITagChoicesListCommand | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tagChoicesListCommand }) => {
      this.tagChoicesListCommand = tagChoicesListCommand;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
