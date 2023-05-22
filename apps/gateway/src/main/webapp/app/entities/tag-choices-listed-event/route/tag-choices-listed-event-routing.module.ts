import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TagChoicesListedEventComponent } from '../list/tag-choices-listed-event.component';
import { TagChoicesListedEventDetailComponent } from '../detail/tag-choices-listed-event-detail.component';
import { TagChoicesListedEventRoutingResolveService } from './tag-choices-listed-event-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const tagChoicesListedEventRoute: Routes = [
  {
    path: '',
    component: TagChoicesListedEventComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TagChoicesListedEventDetailComponent,
    resolve: {
      tagChoicesListedEvent: TagChoicesListedEventRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tagChoicesListedEventRoute)],
  exports: [RouterModule],
})
export class TagChoicesListedEventRoutingModule {}
