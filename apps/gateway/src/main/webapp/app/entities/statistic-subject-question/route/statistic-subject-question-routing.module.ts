import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { StatisticSubjectQuestionComponent } from '../list/statistic-subject-question.component';
import { StatisticSubjectQuestionDetailComponent } from '../detail/statistic-subject-question-detail.component';
import { StatisticSubjectQuestionRoutingResolveService } from './statistic-subject-question-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const statisticSubjectQuestionRoute: Routes = [
  {
    path: '',
    component: StatisticSubjectQuestionComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StatisticSubjectQuestionDetailComponent,
    resolve: {
      statisticSubjectQuestion: StatisticSubjectQuestionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(statisticSubjectQuestionRoute)],
  exports: [RouterModule],
})
export class StatisticSubjectQuestionRoutingModule {}
