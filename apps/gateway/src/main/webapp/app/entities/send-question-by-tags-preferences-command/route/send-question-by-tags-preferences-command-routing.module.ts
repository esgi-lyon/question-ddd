import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SendQuestionByTagsPreferencesCommandComponent } from '../list/send-question-by-tags-preferences-command.component';
import { SendQuestionByTagsPreferencesCommandDetailComponent } from '../detail/send-question-by-tags-preferences-command-detail.component';
import { SendQuestionByTagsPreferencesCommandUpdateComponent } from '../update/send-question-by-tags-preferences-command-update.component';
import { SendQuestionByTagsPreferencesCommandRoutingResolveService } from './send-question-by-tags-preferences-command-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const sendQuestionByTagsPreferencesCommandRoute: Routes = [
  {
    path: '',
    component: SendQuestionByTagsPreferencesCommandComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SendQuestionByTagsPreferencesCommandDetailComponent,
    resolve: {
      sendQuestionByTagsPreferencesCommand: SendQuestionByTagsPreferencesCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SendQuestionByTagsPreferencesCommandUpdateComponent,
    resolve: {
      sendQuestionByTagsPreferencesCommand: SendQuestionByTagsPreferencesCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SendQuestionByTagsPreferencesCommandUpdateComponent,
    resolve: {
      sendQuestionByTagsPreferencesCommand: SendQuestionByTagsPreferencesCommandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sendQuestionByTagsPreferencesCommandRoute)],
  exports: [RouterModule],
})
export class SendQuestionByTagsPreferencesCommandRoutingModule {}
