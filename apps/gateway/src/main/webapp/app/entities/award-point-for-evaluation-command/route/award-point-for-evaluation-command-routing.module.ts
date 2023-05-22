import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AwardPointForEvaluationCommandComponent } from '../list/award-point-for-evaluation-command.component';
import { AwardPointForEvaluationCommandDetailComponent } from '../detail/award-point-for-evaluation-command-detail.component';
import { AwardPointForEvaluationCommandUpdateComponent } from '../update/award-point-for-evaluation-command-update.component';
import { AwardPointForEvaluationCommandRoutingResolveService } from './award-point-for-evaluation-command-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const awardPointForEvaluationCommandRoute: Routes = [
  {
    path: '',
    component: AwardPointForEvaluationCommandComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AwardPointForEvaluationCommandDetailComponent,
    resolve: {
      awardPointForEvaluationCommand: AwardPointForEvaluationCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AwardPointForEvaluationCommandUpdateComponent,
    resolve: {
      awardPointForEvaluationCommand: AwardPointForEvaluationCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AwardPointForEvaluationCommandUpdateComponent,
    resolve: {
      awardPointForEvaluationCommand: AwardPointForEvaluationCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(awardPointForEvaluationCommandRoute)],
  exports: [RouterModule],
})
export class AwardPointForEvaluationCommandRoutingModule {}
