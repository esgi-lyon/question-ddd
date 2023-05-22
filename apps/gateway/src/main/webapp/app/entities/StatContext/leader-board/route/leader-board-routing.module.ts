import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { LeaderBoardComponent } from '../list/leader-board.component';
import { LeaderBoardDetailComponent } from '../detail/leader-board-detail.component';
import { LeaderBoardUpdateComponent } from '../update/leader-board-update.component';
import { LeaderBoardRoutingResolveService } from './leader-board-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const leaderBoardRoute: Routes = [
  {
    path: '',
    component: LeaderBoardComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LeaderBoardDetailComponent,
    resolve: {
      leaderBoard: LeaderBoardRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LeaderBoardUpdateComponent,
    resolve: {
      leaderBoard: LeaderBoardRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LeaderBoardUpdateComponent,
    resolve: {
      leaderBoard: LeaderBoardRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(leaderBoardRoute)],
  exports: [RouterModule],
})
export class LeaderBoardRoutingModule {}
