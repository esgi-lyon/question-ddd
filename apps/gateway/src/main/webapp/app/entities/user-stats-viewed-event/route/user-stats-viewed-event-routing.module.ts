import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { UserStatsViewedEventComponent } from '../list/user-stats-viewed-event.component';
import { UserStatsViewedEventDetailComponent } from '../detail/user-stats-viewed-event-detail.component';
import { UserStatsViewedEventRoutingResolveService } from './user-stats-viewed-event-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const userStatsViewedEventRoute: Routes = [
  {
    path: '',
    component: UserStatsViewedEventComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UserStatsViewedEventDetailComponent,
    resolve: {
      userStatsViewedEvent: UserStatsViewedEventRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(userStatsViewedEventRoute)],
  exports: [RouterModule],
})
export class UserStatsViewedEventRoutingModule {}
