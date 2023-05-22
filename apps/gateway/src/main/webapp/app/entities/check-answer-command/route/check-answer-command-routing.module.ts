import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CheckAnswerCommandComponent } from '../list/check-answer-command.component';
import { CheckAnswerCommandDetailComponent } from '../detail/check-answer-command-detail.component';
import { CheckAnswerCommandUpdateComponent } from '../update/check-answer-command-update.component';
import { CheckAnswerCommandRoutingResolveService } from './check-answer-command-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const checkAnswerCommandRoute: Routes = [
  {
    path: '',
    component: CheckAnswerCommandComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CheckAnswerCommandDetailComponent,
    resolve: {
      checkAnswerCommand: CheckAnswerCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CheckAnswerCommandUpdateComponent,
    resolve: {
      checkAnswerCommand: CheckAnswerCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CheckAnswerCommandUpdateComponent,
    resolve: {
      checkAnswerCommand: CheckAnswerCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(checkAnswerCommandRoute)],
  exports: [RouterModule],
})
export class CheckAnswerCommandRoutingModule {}
