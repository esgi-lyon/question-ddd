import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { StatisticSubjectUserComponent } from '../list/statistic-subject-user.component';
import { StatisticSubjectUserDetailComponent } from '../detail/statistic-subject-user-detail.component';
import { StatisticSubjectUserRoutingResolveService } from './statistic-subject-user-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const statisticSubjectUserRoute: Routes = [
  {
    path: '',
    component: StatisticSubjectUserComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StatisticSubjectUserDetailComponent,
    resolve: {
      statisticSubjectUser: StatisticSubjectUserRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(statisticSubjectUserRoute)],
  exports: [RouterModule],
})
export class StatisticSubjectUserRoutingModule {}
