import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CategoryIdComponent } from '../list/category-id.component';
import { CategoryIdDetailComponent } from '../detail/category-id-detail.component';
import { CategoryIdRoutingResolveService } from './category-id-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const categoryIdRoute: Routes = [
  {
    path: '',
    component: CategoryIdComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CategoryIdDetailComponent,
    resolve: {
      categoryId: CategoryIdRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(categoryIdRoute)],
  exports: [RouterModule],
})
export class CategoryIdRoutingModule {}
