import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AnswerSubmittedEventComponent } from '../list/answer-submitted-event.component';
import { AnswerSubmittedEventDetailComponent } from '../detail/answer-submitted-event-detail.component';
import { AnswerSubmittedEventRoutingResolveService } from './answer-submitted-event-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const answerSubmittedEventRoute: Routes = [
  {
    path: '',
    component: AnswerSubmittedEventComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AnswerSubmittedEventDetailComponent,
    resolve: {
      answerSubmittedEvent: AnswerSubmittedEventRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(answerSubmittedEventRoute)],
  exports: [RouterModule],
})
export class AnswerSubmittedEventRoutingModule {}
