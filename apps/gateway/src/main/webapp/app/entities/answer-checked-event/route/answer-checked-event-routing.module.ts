import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AnswerCheckedEventComponent } from '../list/answer-checked-event.component';
import { AnswerCheckedEventDetailComponent } from '../detail/answer-checked-event-detail.component';
import { AnswerCheckedEventRoutingResolveService } from './answer-checked-event-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const answerCheckedEventRoute: Routes = [
  {
    path: '',
    component: AnswerCheckedEventComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AnswerCheckedEventDetailComponent,
    resolve: {
      answerCheckedEvent: AnswerCheckedEventRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(answerCheckedEventRoute)],
  exports: [RouterModule],
})
export class AnswerCheckedEventRoutingModule {}
