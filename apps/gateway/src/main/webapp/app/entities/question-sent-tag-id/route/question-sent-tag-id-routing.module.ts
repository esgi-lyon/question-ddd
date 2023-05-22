import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { QuestionSentTagIdComponent } from '../list/question-sent-tag-id.component';
import { QuestionSentTagIdDetailComponent } from '../detail/question-sent-tag-id-detail.component';
import { QuestionSentTagIdRoutingResolveService } from './question-sent-tag-id-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const questionSentTagIdRoute: Routes = [
  {
    path: '',
    component: QuestionSentTagIdComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: QuestionSentTagIdDetailComponent,
    resolve: {
      questionSentTagId: QuestionSentTagIdRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(questionSentTagIdRoute)],
  exports: [RouterModule],
})
export class QuestionSentTagIdRoutingModule {}
