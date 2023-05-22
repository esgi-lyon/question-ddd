import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { QuestionIdComponent } from '../list/question-id.component';
import { QuestionIdDetailComponent } from '../detail/question-id-detail.component';
import { QuestionIdRoutingResolveService } from './question-id-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const questionIdRoute: Routes = [
  {
    path: '',
    component: QuestionIdComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: QuestionIdDetailComponent,
    resolve: {
      questionId: QuestionIdRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(questionIdRoute)],
  exports: [RouterModule],
})
export class QuestionIdRoutingModule {}
