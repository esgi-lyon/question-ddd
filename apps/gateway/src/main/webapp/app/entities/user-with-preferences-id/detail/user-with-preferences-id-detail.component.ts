import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserWithPreferencesId } from '../user-with-preferences-id.model';

@Component({
  selector: 'jhi-user-with-preferences-id-detail',
  templateUrl: './user-with-preferences-id-detail.component.html',
})
export class UserWithPreferencesIdDetailComponent implements OnInit {
  userWithPreferencesId: IUserWithPreferencesId | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userWithPreferencesId }) => {
      this.userWithPreferencesId = userWithPreferencesId;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
