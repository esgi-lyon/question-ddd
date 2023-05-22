import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ViewStatsCommandComponent } from '../list/view-stats-command.component';
import { ViewStatsCommandDetailComponent } from '../detail/view-stats-command-detail.component';
import { ViewStatsCommandUpdateComponent } from '../update/view-stats-command-update.component';
import { ViewStatsCommandRoutingResolveService } from './view-stats-command-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const viewStatsCommandRoute: Routes = [
  {
    path: '',
    component: ViewStatsCommandComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ViewStatsCommandDetailComponent,
    resolve: {
      viewStatsCommand: ViewStatsCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ViewStatsCommandUpdateComponent,
    resolve: {
      viewStatsCommand: ViewStatsCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ViewStatsCommandUpdateComponent,
    resolve: {
      viewStatsCommand: ViewStatsCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(viewStatsCommandRoute)],
  exports: [RouterModule],
})
export class ViewStatsCommandRoutingModule {}
