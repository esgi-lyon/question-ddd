import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserInfos } from '../user-infos.model';

@Component({
  selector: 'jhi-user-infos-detail',
  templateUrl: './user-infos-detail.component.html',
})
export class UserInfosDetailComponent implements OnInit {
  userInfos: IUserInfos | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userInfos }) => {
      this.userInfos = userInfos;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
