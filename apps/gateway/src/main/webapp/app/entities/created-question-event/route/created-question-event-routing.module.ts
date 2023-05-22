import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CreatedQuestionEventComponent } from '../list/created-question-event.component';
import { CreatedQuestionEventDetailComponent } from '../detail/created-question-event-detail.component';
import { CreatedQuestionEventRoutingResolveService } from './created-question-event-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const createdQuestionEventRoute: Routes = [
  {
    path: '',
    component: CreatedQuestionEventComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CreatedQuestionEventDetailComponent,
    resolve: {
      createdQuestionEvent: CreatedQuestionEventRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(createdQuestionEventRoute)],
  exports: [RouterModule],
})
export class CreatedQuestionEventRoutingModule {}
