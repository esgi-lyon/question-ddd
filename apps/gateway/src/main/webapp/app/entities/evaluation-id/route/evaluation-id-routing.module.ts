import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EvaluationIdComponent } from '../list/evaluation-id.component';
import { EvaluationIdDetailComponent } from '../detail/evaluation-id-detail.component';
import { EvaluationIdRoutingResolveService } from './evaluation-id-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const evaluationIdRoute: Routes = [
  {
    path: '',
    component: EvaluationIdComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EvaluationIdDetailComponent,
    resolve: {
      evaluationId: EvaluationIdRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(evaluationIdRoute)],
  exports: [RouterModule],
})
export class EvaluationIdRoutingModule {}
