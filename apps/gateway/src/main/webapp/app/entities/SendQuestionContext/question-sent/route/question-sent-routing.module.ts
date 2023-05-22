import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { QuestionSentComponent } from '../list/question-sent.component';
import { QuestionSentDetailComponent } from '../detail/question-sent-detail.component';
import { QuestionSentUpdateComponent } from '../update/question-sent-update.component';
import { QuestionSentRoutingResolveService } from './question-sent-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const questionSentRoute: Routes = [
  {
    path: '',
    component: QuestionSentComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: QuestionSentDetailComponent,
    resolve: {
      questionSent: QuestionSentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: QuestionSentUpdateComponent,
    resolve: {
      questionSent: QuestionSentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: QuestionSentUpdateComponent,
    resolve: {
      questionSent: QuestionSentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(questionSentRoute)],
  exports: [RouterModule],
})
export class QuestionSentRoutingModule {}
