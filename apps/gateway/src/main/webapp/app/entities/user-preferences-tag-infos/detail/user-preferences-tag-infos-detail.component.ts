import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserPreferencesTagInfos } from '../user-preferences-tag-infos.model';

@Component({
  selector: 'jhi-user-preferences-tag-infos-detail',
  templateUrl: './user-preferences-tag-infos-detail.component.html',
})
export class UserPreferencesTagInfosDetailComponent implements OnInit {
  userPreferencesTagInfos: IUserPreferencesTagInfos | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userPreferencesTagInfos }) => {
      this.userPreferencesTagInfos = userPreferencesTagInfos;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
