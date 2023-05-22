import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { RejectResourceTagCommandComponent } from '../list/reject-resource-tag-command.component';
import { RejectResourceTagCommandDetailComponent } from '../detail/reject-resource-tag-command-detail.component';
import { RejectResourceTagCommandUpdateComponent } from '../update/reject-resource-tag-command-update.component';
import { RejectResourceTagCommandRoutingResolveService } from './reject-resource-tag-command-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const rejectResourceTagCommandRoute: Routes = [
  {
    path: '',
    component: RejectResourceTagCommandComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RejectResourceTagCommandDetailComponent,
    resolve: {
      rejectResourceTagCommand: RejectResourceTagCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RejectResourceTagCommandUpdateComponent,
    resolve: {
      rejectResourceTagCommand: RejectResourceTagCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RejectResourceTagCommandUpdateComponent,
    resolve: {
      rejectResourceTagCommand: RejectResourceTagCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(rejectResourceTagCommandRoute)],
  exports: [RouterModule],
})
export class RejectResourceTagCommandRoutingModule {}
