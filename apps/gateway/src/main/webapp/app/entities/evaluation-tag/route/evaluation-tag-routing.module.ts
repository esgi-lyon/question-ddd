import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EvaluationTagComponent } from '../list/evaluation-tag.component';
import { EvaluationTagDetailComponent } from '../detail/evaluation-tag-detail.component';
import { EvaluationTagRoutingResolveService } from './evaluation-tag-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const evaluationTagRoute: Routes = [
  {
    path: '',
    component: EvaluationTagComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EvaluationTagDetailComponent,
    resolve: {
      evaluationTag: EvaluationTagRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(evaluationTagRoute)],
  exports: [RouterModule],
})
export class EvaluationTagRoutingModule {}
