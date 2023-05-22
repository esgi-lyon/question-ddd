import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILeaderBoard } from '../leader-board.model';

@Component({
  selector: 'jhi-leader-board-detail',
  templateUrl: './leader-board-detail.component.html',
})
export class LeaderBoardDetailComponent implements OnInit {
  leaderBoard: ILeaderBoard | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ leaderBoard }) => {
      this.leaderBoard = leaderBoard;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
