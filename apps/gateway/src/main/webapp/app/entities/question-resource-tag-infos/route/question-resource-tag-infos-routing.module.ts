import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { QuestionResourceTagInfosComponent } from '../list/question-resource-tag-infos.component';
import { QuestionResourceTagInfosDetailComponent } from '../detail/question-resource-tag-infos-detail.component';
import { QuestionResourceTagInfosRoutingResolveService } from './question-resource-tag-infos-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const questionResourceTagInfosRoute: Routes = [
  {
    path: '',
    component: QuestionResourceTagInfosComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: QuestionResourceTagInfosDetailComponent,
    resolve: {
      questionResourceTagInfos: QuestionResourceTagInfosRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(questionResourceTagInfosRoute)],
  exports: [RouterModule],
})
export class QuestionResourceTagInfosRoutingModule {}
