import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAnsweringUser } from '../answering-user.model';

@Component({
  selector: 'jhi-answering-user-detail',
  templateUrl: './answering-user-detail.component.html',
})
export class AnsweringUserDetailComponent implements OnInit {
  answeringUser: IAnsweringUser | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ answeringUser }) => {
      this.answeringUser = answeringUser;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
