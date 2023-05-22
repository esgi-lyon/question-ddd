import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EvaluatedAnswerComponent } from '../list/evaluated-answer.component';
import { EvaluatedAnswerDetailComponent } from '../detail/evaluated-answer-detail.component';
import { EvaluatedAnswerRoutingResolveService } from './evaluated-answer-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const evaluatedAnswerRoute: Routes = [
  {
    path: '',
    component: EvaluatedAnswerComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EvaluatedAnswerDetailComponent,
    resolve: {
      evaluatedAnswer: EvaluatedAnswerRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(evaluatedAnswerRoute)],
  exports: [RouterModule],
})
export class EvaluatedAnswerRoutingModule {}
