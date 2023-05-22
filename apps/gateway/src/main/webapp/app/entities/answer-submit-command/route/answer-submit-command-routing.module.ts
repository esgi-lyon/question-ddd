import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AnswerSubmitCommandComponent } from '../list/answer-submit-command.component';
import { AnswerSubmitCommandDetailComponent } from '../detail/answer-submit-command-detail.component';
import { AnswerSubmitCommandUpdateComponent } from '../update/answer-submit-command-update.component';
import { AnswerSubmitCommandRoutingResolveService } from './answer-submit-command-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const answerSubmitCommandRoute: Routes = [
  {
    path: '',
    component: AnswerSubmitCommandComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AnswerSubmitCommandDetailComponent,
    resolve: {
      answerSubmitCommand: AnswerSubmitCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AnswerSubmitCommandUpdateComponent,
    resolve: {
      answerSubmitCommand: AnswerSubmitCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AnswerSubmitCommandUpdateComponent,
    resolve: {
      answerSubmitCommand: AnswerSubmitCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(answerSubmitCommandRoute)],
  exports: [RouterModule],
})
export class AnswerSubmitCommandRoutingModule {}
