import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EvaluationQuestionComponent } from '../list/evaluation-question.component';
import { EvaluationQuestionDetailComponent } from '../detail/evaluation-question-detail.component';
import { EvaluationQuestionRoutingResolveService } from './evaluation-question-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const evaluationQuestionRoute: Routes = [
  {
    path: '',
    component: EvaluationQuestionComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EvaluationQuestionDetailComponent,
    resolve: {
      evaluationQuestion: EvaluationQuestionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(evaluationQuestionRoute)],
  exports: [RouterModule],
})
export class EvaluationQuestionRoutingModule {}
