import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CreateResourceCommandComponent } from '../list/create-resource-command.component';
import { CreateResourceCommandDetailComponent } from '../detail/create-resource-command-detail.component';
import { CreateResourceCommandUpdateComponent } from '../update/create-resource-command-update.component';
import { CreateResourceCommandRoutingResolveService } from './create-resource-command-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const createResourceCommandRoute: Routes = [
  {
    path: '',
    component: CreateResourceCommandComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CreateResourceCommandDetailComponent,
    resolve: {
      createResourceCommand: CreateResourceCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CreateResourceCommandUpdateComponent,
    resolve: {
      createResourceCommand: CreateResourceCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CreateResourceCommandUpdateComponent,
    resolve: {
      createResourceCommand: CreateResourceCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(createResourceCommandRoute)],
  exports: [RouterModule],
})
export class CreateResourceCommandRoutingModule {}
