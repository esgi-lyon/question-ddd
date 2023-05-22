import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { LeaderBoardComponent } from './list/leader-board.component';
import { LeaderBoardDetailComponent } from './detail/leader-board-detail.component';
import { LeaderBoardUpdateComponent } from './update/leader-board-update.component';
import { LeaderBoardDeleteDialogComponent } from './delete/leader-board-delete-dialog.component';
import { LeaderBoardRoutingModule } from './route/leader-board-routing.module';

@NgModule({
  imports: [SharedModule, LeaderBoardRoutingModule],
  declarations: [LeaderBoardComponent, LeaderBoardDetailComponent, LeaderBoardUpdateComponent, LeaderBoardDeleteDialogComponent],
})
export class StatContextLeaderBoardModule {}
