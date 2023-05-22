import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CategoryCreatedEventComponent } from '../list/category-created-event.component';
import { CategoryCreatedEventDetailComponent } from '../detail/category-created-event-detail.component';
import { CategoryCreatedEventRoutingResolveService } from './category-created-event-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const categoryCreatedEventRoute: Routes = [
  {
    path: '',
    component: CategoryCreatedEventComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CategoryCreatedEventDetailComponent,
    resolve: {
      categoryCreatedEvent: CategoryCreatedEventRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(categoryCreatedEventRoute)],
  exports: [RouterModule],
})
export class CategoryCreatedEventRoutingModule {}
