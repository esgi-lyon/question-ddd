import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { UserPreferencesTagInfosComponent } from '../list/user-preferences-tag-infos.component';
import { UserPreferencesTagInfosDetailComponent } from '../detail/user-preferences-tag-infos-detail.component';
import { UserPreferencesTagInfosRoutingResolveService } from './user-preferences-tag-infos-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const userPreferencesTagInfosRoute: Routes = [
  {
    path: '',
    component: UserPreferencesTagInfosComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UserPreferencesTagInfosDetailComponent,
    resolve: {
      userPreferencesTagInfos: UserPreferencesTagInfosRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(userPreferencesTagInfosRoute)],
  exports: [RouterModule],
})
export class UserPreferencesTagInfosRoutingModule {}
