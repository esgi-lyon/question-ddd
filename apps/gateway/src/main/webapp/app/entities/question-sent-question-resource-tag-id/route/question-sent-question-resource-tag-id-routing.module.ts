import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { QuestionSentQuestionResourceTagIdComponent } from '../list/question-sent-question-resource-tag-id.component';
import { QuestionSentQuestionResourceTagIdDetailComponent } from '../detail/question-sent-question-resource-tag-id-detail.component';
import { QuestionSentQuestionResourceTagIdRoutingResolveService } from './question-sent-question-resource-tag-id-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const questionSentQuestionResourceTagIdRoute: Routes = [
  {
    path: '',
    component: QuestionSentQuestionResourceTagIdComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: QuestionSentQuestionResourceTagIdDetailComponent,
    resolve: {
      questionSentQuestionResourceTagId: QuestionSentQuestionResourceTagIdRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(questionSentQuestionResourceTagIdRoute)],
  exports: [RouterModule],
})
export class QuestionSentQuestionResourceTagIdRoutingModule {}
