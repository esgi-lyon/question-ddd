import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { QuestionResourceComponent } from '../list/question-resource.component';
import { QuestionResourceDetailComponent } from '../detail/question-resource-detail.component';
import { QuestionResourceUpdateComponent } from '../update/question-resource-update.component';
import { QuestionResourceRoutingResolveService } from './question-resource-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const questionResourceRoute: Routes = [
  {
    path: '',
    component: QuestionResourceComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: QuestionResourceDetailComponent,
    resolve: {
      questionResource: QuestionResourceRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: QuestionResourceUpdateComponent,
    resolve: {
      questionResource: QuestionResourceRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: QuestionResourceUpdateComponent,
    resolve: {
      questionResource: QuestionResourceRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(questionResourceRoute)],
  exports: [RouterModule],
})
export class QuestionResourceRoutingModule {}
