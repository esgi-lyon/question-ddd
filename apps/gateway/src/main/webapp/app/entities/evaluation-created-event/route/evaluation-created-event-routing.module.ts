import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EvaluationCreatedEventComponent } from '../list/evaluation-created-event.component';
import { EvaluationCreatedEventDetailComponent } from '../detail/evaluation-created-event-detail.component';
import { EvaluationCreatedEventRoutingResolveService } from './evaluation-created-event-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const evaluationCreatedEventRoute: Routes = [
  {
    path: '',
    component: EvaluationCreatedEventComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EvaluationCreatedEventDetailComponent,
    resolve: {
      evaluationCreatedEvent: EvaluationCreatedEventRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(evaluationCreatedEventRoute)],
  exports: [RouterModule],
})
export class EvaluationCreatedEventRoutingModule {}
