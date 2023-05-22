import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ValidateResourceTagLinkageCommandComponent } from '../list/validate-resource-tag-linkage-command.component';
import { ValidateResourceTagLinkageCommandDetailComponent } from '../detail/validate-resource-tag-linkage-command-detail.component';
import { ValidateResourceTagLinkageCommandUpdateComponent } from '../update/validate-resource-tag-linkage-command-update.component';
import { ValidateResourceTagLinkageCommandRoutingResolveService } from './validate-resource-tag-linkage-command-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const validateResourceTagLinkageCommandRoute: Routes = [
  {
    path: '',
    component: ValidateResourceTagLinkageCommandComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ValidateResourceTagLinkageCommandDetailComponent,
    resolve: {
      validateResourceTagLinkageCommand: ValidateResourceTagLinkageCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ValidateResourceTagLinkageCommandUpdateComponent,
    resolve: {
      validateResourceTagLinkageCommand: ValidateResourceTagLinkageCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ValidateResourceTagLinkageCommandUpdateComponent,
    resolve: {
      validateResourceTagLinkageCommand: ValidateResourceTagLinkageCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(validateResourceTagLinkageCommandRoute)],
  exports: [RouterModule],
})
export class ValidateResourceTagLinkageCommandRoutingModule {}
