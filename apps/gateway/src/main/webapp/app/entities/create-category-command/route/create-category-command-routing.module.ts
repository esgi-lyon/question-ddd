import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CreateCategoryCommandComponent } from '../list/create-category-command.component';
import { CreateCategoryCommandDetailComponent } from '../detail/create-category-command-detail.component';
import { CreateCategoryCommandUpdateComponent } from '../update/create-category-command-update.component';
import { CreateCategoryCommandRoutingResolveService } from './create-category-command-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const createCategoryCommandRoute: Routes = [
  {
    path: '',
    component: CreateCategoryCommandComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CreateCategoryCommandDetailComponent,
    resolve: {
      createCategoryCommand: CreateCategoryCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CreateCategoryCommandUpdateComponent,
    resolve: {
      createCategoryCommand: CreateCategoryCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CreateCategoryCommandUpdateComponent,
    resolve: {
      createCategoryCommand: CreateCategoryCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(createCategoryCommandRoute)],
  exports: [RouterModule],
})
export class CreateCategoryCommandRoutingModule {}
