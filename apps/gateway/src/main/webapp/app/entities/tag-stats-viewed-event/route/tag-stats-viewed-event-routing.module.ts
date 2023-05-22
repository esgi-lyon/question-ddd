import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TagStatsViewedEventComponent } from '../list/tag-stats-viewed-event.component';
import { TagStatsViewedEventDetailComponent } from '../detail/tag-stats-viewed-event-detail.component';
import { TagStatsViewedEventRoutingResolveService } from './tag-stats-viewed-event-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const tagStatsViewedEventRoute: Routes = [
  {
    path: '',
    component: TagStatsViewedEventComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TagStatsViewedEventDetailComponent,
    resolve: {
      tagStatsViewedEvent: TagStatsViewedEventRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tagStatsViewedEventRoute)],
  exports: [RouterModule],
})
export class TagStatsViewedEventRoutingModule {}
