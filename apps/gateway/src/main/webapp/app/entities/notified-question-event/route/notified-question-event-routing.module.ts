import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { NotifiedQuestionEventComponent } from '../list/notified-question-event.component';
import { NotifiedQuestionEventDetailComponent } from '../detail/notified-question-event-detail.component';
import { NotifiedQuestionEventRoutingResolveService } from './notified-question-event-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const notifiedQuestionEventRoute: Routes = [
  {
    path: '',
    component: NotifiedQuestionEventComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NotifiedQuestionEventDetailComponent,
    resolve: {
      notifiedQuestionEvent: NotifiedQuestionEventRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(notifiedQuestionEventRoute)],
  exports: [RouterModule],
})
export class NotifiedQuestionEventRoutingModule {}
