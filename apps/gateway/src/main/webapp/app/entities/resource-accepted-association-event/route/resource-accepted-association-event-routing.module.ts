import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ResourceAcceptedAssociationEventComponent } from '../list/resource-accepted-association-event.component';
import { ResourceAcceptedAssociationEventDetailComponent } from '../detail/resource-accepted-association-event-detail.component';
import { ResourceAcceptedAssociationEventRoutingResolveService } from './resource-accepted-association-event-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const resourceAcceptedAssociationEventRoute: Routes = [
  {
    path: '',
    component: ResourceAcceptedAssociationEventComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ResourceAcceptedAssociationEventDetailComponent,
    resolve: {
      resourceAcceptedAssociationEvent: ResourceAcceptedAssociationEventRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(resourceAcceptedAssociationEventRoute)],
  exports: [RouterModule],
})
export class ResourceAcceptedAssociationEventRoutingModule {}
