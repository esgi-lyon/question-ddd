import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AwardedPointEventComponent } from '../list/awarded-point-event.component';
import { AwardedPointEventDetailComponent } from '../detail/awarded-point-event-detail.component';
import { AwardedPointEventRoutingResolveService } from './awarded-point-event-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const awardedPointEventRoute: Routes = [
  {
    path: '',
    component: AwardedPointEventComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AwardedPointEventDetailComponent,
    resolve: {
      awardedPointEvent: AwardedPointEventRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(awardedPointEventRoute)],
  exports: [RouterModule],
})
export class AwardedPointEventRoutingModule {}
