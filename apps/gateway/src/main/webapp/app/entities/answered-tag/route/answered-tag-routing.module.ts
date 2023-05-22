import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AnsweredTagComponent } from '../list/answered-tag.component';
import { AnsweredTagDetailComponent } from '../detail/answered-tag-detail.component';
import { AnsweredTagRoutingResolveService } from './answered-tag-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const answeredTagRoute: Routes = [
  {
    path: '',
    component: AnsweredTagComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AnsweredTagDetailComponent,
    resolve: {
      answeredTag: AnsweredTagRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(answeredTagRoute)],
  exports: [RouterModule],
})
export class AnsweredTagRoutingModule {}
