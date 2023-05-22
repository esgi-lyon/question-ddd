import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ResourceWaitingForAssociationEventComponent } from '../list/resource-waiting-for-association-event.component';
import { ResourceWaitingForAssociationEventDetailComponent } from '../detail/resource-waiting-for-association-event-detail.component';
import { ResourceWaitingForAssociationEventRoutingResolveService } from './resource-waiting-for-association-event-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const resourceWaitingForAssociationEventRoute: Routes = [
  {
    path: '',
    component: ResourceWaitingForAssociationEventComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ResourceWaitingForAssociationEventDetailComponent,
    resolve: {
      resourceWaitingForAssociationEvent: ResourceWaitingForAssociationEventRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(resourceWaitingForAssociationEventRoute)],
  exports: [RouterModule],
})
export class ResourceWaitingForAssociationEventRoutingModule {}
