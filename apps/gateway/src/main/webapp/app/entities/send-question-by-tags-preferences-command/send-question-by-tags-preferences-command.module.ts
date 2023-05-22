import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { SendQuestionByTagsPreferencesCommandComponent } from './list/send-question-by-tags-preferences-command.component';
import { SendQuestionByTagsPreferencesCommandDetailComponent } from './detail/send-question-by-tags-preferences-command-detail.component';
import { SendQuestionByTagsPreferencesCommandUpdateComponent } from './update/send-question-by-tags-preferences-command-update.component';
import { SendQuestionByTagsPreferencesCommandDeleteDialogComponent } from './delete/send-question-by-tags-preferences-command-delete-dialog.component';
import { SendQuestionByTagsPreferencesCommandRoutingModule } from './route/send-question-by-tags-preferences-command-routing.module';

@NgModule({
  imports: [SharedModule, SendQuestionByTagsPreferencesCommandRoutingModule],
  declarations: [
    SendQuestionByTagsPreferencesCommandComponent,
    SendQuestionByTagsPreferencesCommandDetailComponent,
    SendQuestionByTagsPreferencesCommandUpdateComponent,
    SendQuestionByTagsPreferencesCommandDeleteDialogComponent,
  ],
})
export class SendQuestionByTagsPreferencesCommandModule {}
