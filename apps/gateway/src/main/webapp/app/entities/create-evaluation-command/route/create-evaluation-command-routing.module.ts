import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CreateEvaluationCommandComponent } from '../list/create-evaluation-command.component';
import { CreateEvaluationCommandDetailComponent } from '../detail/create-evaluation-command-detail.component';
import { CreateEvaluationCommandUpdateComponent } from '../update/create-evaluation-command-update.component';
import { CreateEvaluationCommandRoutingResolveService } from './create-evaluation-command-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const createEvaluationCommandRoute: Routes = [
  {
    path: '',
    component: CreateEvaluationCommandComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CreateEvaluationCommandDetailComponent,
    resolve: {
      createEvaluationCommand: CreateEvaluationCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CreateEvaluationCommandUpdateComponent,
    resolve: {
      createEvaluationCommand: CreateEvaluationCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CreateEvaluationCommandUpdateComponent,
    resolve: {
      createEvaluationCommand: CreateEvaluationCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(createEvaluationCommandRoute)],
  exports: [RouterModule],
})
export class CreateEvaluationCommandRoutingModule {}
