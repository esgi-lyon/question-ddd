import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAnsweredTag } from '../answered-tag.model';

@Component({
  selector: 'jhi-answered-tag-detail',
  templateUrl: './answered-tag-detail.component.html',
})
export class AnsweredTagDetailComponent implements OnInit {
  answeredTag: IAnsweredTag | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ answeredTag }) => {
      this.answeredTag = answeredTag;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
