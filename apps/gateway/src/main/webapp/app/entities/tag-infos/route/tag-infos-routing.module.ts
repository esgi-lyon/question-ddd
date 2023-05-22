import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TagInfosComponent } from '../list/tag-infos.component';
import { TagInfosDetailComponent } from '../detail/tag-infos-detail.component';
import { TagInfosRoutingResolveService } from './tag-infos-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const tagInfosRoute: Routes = [
  {
    path: '',
    component: TagInfosComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TagInfosDetailComponent,
    resolve: {
      tagInfos: TagInfosRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tagInfosRoute)],
  exports: [RouterModule],
})
export class TagInfosRoutingModule {}
