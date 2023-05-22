import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { QuestionSentTagInfosComponent } from '../list/question-sent-tag-infos.component';
import { QuestionSentTagInfosDetailComponent } from '../detail/question-sent-tag-infos-detail.component';
import { QuestionSentTagInfosRoutingResolveService } from './question-sent-tag-infos-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const questionSentTagInfosRoute: Routes = [
  {
    path: '',
    component: QuestionSentTagInfosComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: QuestionSentTagInfosDetailComponent,
    resolve: {
      questionSentTagInfos: QuestionSentTagInfosRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(questionSentTagInfosRoute)],
  exports: [RouterModule],
})
export class QuestionSentTagInfosRoutingModule {}
