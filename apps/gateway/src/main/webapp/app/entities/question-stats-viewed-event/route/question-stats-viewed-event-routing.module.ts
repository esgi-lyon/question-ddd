import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { QuestionStatsViewedEventComponent } from '../list/question-stats-viewed-event.component';
import { QuestionStatsViewedEventDetailComponent } from '../detail/question-stats-viewed-event-detail.component';
import { QuestionStatsViewedEventRoutingResolveService } from './question-stats-viewed-event-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const questionStatsViewedEventRoute: Routes = [
  {
    path: '',
    component: QuestionStatsViewedEventComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: QuestionStatsViewedEventDetailComponent,
    resolve: {
      questionStatsViewedEvent: QuestionStatsViewedEventRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(questionStatsViewedEventRoute)],
  exports: [RouterModule],
})
export class QuestionStatsViewedEventRoutingModule {}
