import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { UserInfosComponent } from '../list/user-infos.component';
import { UserInfosDetailComponent } from '../detail/user-infos-detail.component';
import { UserInfosUpdateComponent } from '../update/user-infos-update.component';
import { UserInfosRoutingResolveService } from './user-infos-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const userInfosRoute: Routes = [
  {
    path: '',
    component: UserInfosComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UserInfosDetailComponent,
    resolve: {
      userInfos: UserInfosRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UserInfosUpdateComponent,
    resolve: {
      userInfos: UserInfosRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UserInfosUpdateComponent,
    resolve: {
      userInfos: UserInfosRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(userInfosRoute)],
  exports: [RouterModule],
})
export class UserInfosRoutingModule {}
