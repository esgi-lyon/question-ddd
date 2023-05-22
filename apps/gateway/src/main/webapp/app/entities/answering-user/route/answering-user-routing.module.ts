import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AnsweringUserComponent } from '../list/answering-user.component';
import { AnsweringUserDetailComponent } from '../detail/answering-user-detail.component';
import { AnsweringUserRoutingResolveService } from './answering-user-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const answeringUserRoute: Routes = [
  {
    path: '',
    component: AnsweringUserComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AnsweringUserDetailComponent,
    resolve: {
      answeringUser: AnsweringUserRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(answeringUserRoute)],
  exports: [RouterModule],
})
export class AnsweringUserRoutingModule {}
