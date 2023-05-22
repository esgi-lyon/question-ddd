import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TagChoicesListCommandComponent } from '../list/tag-choices-list-command.component';
import { TagChoicesListCommandDetailComponent } from '../detail/tag-choices-list-command-detail.component';
import { TagChoicesListCommandUpdateComponent } from '../update/tag-choices-list-command-update.component';
import { TagChoicesListCommandRoutingResolveService } from './tag-choices-list-command-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const tagChoicesListCommandRoute: Routes = [
  {
    path: '',
    component: TagChoicesListCommandComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TagChoicesListCommandDetailComponent,
    resolve: {
      tagChoicesListCommand: TagChoicesListCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TagChoicesListCommandUpdateComponent,
    resolve: {
      tagChoicesListCommand: TagChoicesListCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TagChoicesListCommandUpdateComponent,
    resolve: {
      tagChoicesListCommand: TagChoicesListCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tagChoicesListCommandRoute)],
  exports: [RouterModule],
})
export class TagChoicesListCommandRoutingModule {}
