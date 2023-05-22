import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ILeaderBoard } from '../leader-board.model';
import { LeaderBoardService } from '../service/leader-board.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './leader-board-delete-dialog.component.html',
})
export class LeaderBoardDeleteDialogComponent {
  leaderBoard?: ILeaderBoard;

  constructor(protected leaderBoardService: LeaderBoardService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.leaderBoardService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
