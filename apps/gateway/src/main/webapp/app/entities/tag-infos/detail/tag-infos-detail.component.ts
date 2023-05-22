import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITagInfos } from '../tag-infos.model';

@Component({
  selector: 'jhi-tag-infos-detail',
  templateUrl: './tag-infos-detail.component.html',
})
export class TagInfosDetailComponent implements OnInit {
  tagInfos: ITagInfos | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tagInfos }) => {
      this.tagInfos = tagInfos;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
