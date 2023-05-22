import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TagCreatedEventComponent } from '../list/tag-created-event.component';
import { TagCreatedEventDetailComponent } from '../detail/tag-created-event-detail.component';
import { TagCreatedEventRoutingResolveService } from './tag-created-event-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const tagCreatedEventRoute: Routes = [
  {
    path: '',
    component: TagCreatedEventComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TagCreatedEventDetailComponent,
    resolve: {
      tagCreatedEvent: TagCreatedEventRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tagCreatedEventRoute)],
  exports: [RouterModule],
})
export class TagCreatedEventRoutingModule {}
