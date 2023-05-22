import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { UserWithPreferencesIdComponent } from '../list/user-with-preferences-id.component';
import { UserWithPreferencesIdDetailComponent } from '../detail/user-with-preferences-id-detail.component';
import { UserWithPreferencesIdRoutingResolveService } from './user-with-preferences-id-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const userWithPreferencesIdRoute: Routes = [
  {
    path: '',
    component: UserWithPreferencesIdComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UserWithPreferencesIdDetailComponent,
    resolve: {
      userWithPreferencesId: UserWithPreferencesIdRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(userWithPreferencesIdRoute)],
  exports: [RouterModule],
})
export class UserWithPreferencesIdRoutingModule {}
