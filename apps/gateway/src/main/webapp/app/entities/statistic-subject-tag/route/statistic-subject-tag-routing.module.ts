import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { StatisticSubjectTagComponent } from '../list/statistic-subject-tag.component';
import { StatisticSubjectTagDetailComponent } from '../detail/statistic-subject-tag-detail.component';
import { StatisticSubjectTagRoutingResolveService } from './statistic-subject-tag-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const statisticSubjectTagRoute: Routes = [
  {
    path: '',
    component: StatisticSubjectTagComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StatisticSubjectTagDetailComponent,
    resolve: {
      statisticSubjectTag: StatisticSubjectTagRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(statisticSubjectTagRoute)],
  exports: [RouterModule],
})
export class StatisticSubjectTagRoutingModule {}
