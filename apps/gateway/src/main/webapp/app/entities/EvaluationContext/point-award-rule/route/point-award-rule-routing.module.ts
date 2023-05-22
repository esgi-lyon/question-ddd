import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { PointAwardRuleComponent } from '../list/point-award-rule.component';
import { PointAwardRuleDetailComponent } from '../detail/point-award-rule-detail.component';
import { PointAwardRuleUpdateComponent } from '../update/point-award-rule-update.component';
import { PointAwardRuleRoutingResolveService } from './point-award-rule-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const pointAwardRuleRoute: Routes = [
  {
    path: '',
    component: PointAwardRuleComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PointAwardRuleDetailComponent,
    resolve: {
      pointAwardRule: PointAwardRuleRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PointAwardRuleUpdateComponent,
    resolve: {
      pointAwardRule: PointAwardRuleRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PointAwardRuleUpdateComponent,
    resolve: {
      pointAwardRule: PointAwardRuleRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(pointAwardRuleRoute)],
  exports: [RouterModule],
})
export class PointAwardRuleRoutingModule {}
