import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CreateQuestionCommandComponent } from '../list/create-question-command.component';
import { CreateQuestionCommandDetailComponent } from '../detail/create-question-command-detail.component';
import { CreateQuestionCommandUpdateComponent } from '../update/create-question-command-update.component';
import { CreateQuestionCommandRoutingResolveService } from './create-question-command-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const createQuestionCommandRoute: Routes = [
  {
    path: '',
    component: CreateQuestionCommandComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CreateQuestionCommandDetailComponent,
    resolve: {
      createQuestionCommand: CreateQuestionCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CreateQuestionCommandUpdateComponent,
    resolve: {
      createQuestionCommand: CreateQuestionCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CreateQuestionCommandUpdateComponent,
    resolve: {
      createQuestionCommand: CreateQuestionCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(createQuestionCommandRoute)],
  exports: [RouterModule],
})
export class CreateQuestionCommandRoutingModule {}
