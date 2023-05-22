import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ResourceRefusedAssociationEventComponent } from '../list/resource-refused-association-event.component';
import { ResourceRefusedAssociationEventDetailComponent } from '../detail/resource-refused-association-event-detail.component';
import { ResourceRefusedAssociationEventRoutingResolveService } from './resource-refused-association-event-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const resourceRefusedAssociationEventRoute: Routes = [
  {
    path: '',
    component: ResourceRefusedAssociationEventComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ResourceRefusedAssociationEventDetailComponent,
    resolve: {
      resourceRefusedAssociationEvent: ResourceRefusedAssociationEventRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(resourceRefusedAssociationEventRoute)],
  exports: [RouterModule],
})
export class ResourceRefusedAssociationEventRoutingModule {}
