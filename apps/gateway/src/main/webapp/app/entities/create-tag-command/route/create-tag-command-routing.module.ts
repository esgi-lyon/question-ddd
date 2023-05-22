import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CreateTagCommandComponent } from '../list/create-tag-command.component';
import { CreateTagCommandDetailComponent } from '../detail/create-tag-command-detail.component';
import { CreateTagCommandUpdateComponent } from '../update/create-tag-command-update.component';
import { CreateTagCommandRoutingResolveService } from './create-tag-command-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const createTagCommandRoute: Routes = [
  {
    path: '',
    component: CreateTagCommandComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CreateTagCommandDetailComponent,
    resolve: {
      createTagCommand: CreateTagCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CreateTagCommandUpdateComponent,
    resolve: {
      createTagCommand: CreateTagCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CreateTagCommandUpdateComponent,
    resolve: {
      createTagCommand: CreateTagCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(createTagCommandRoute)],
  exports: [RouterModule],
})
export class CreateTagCommandRoutingModule {}
