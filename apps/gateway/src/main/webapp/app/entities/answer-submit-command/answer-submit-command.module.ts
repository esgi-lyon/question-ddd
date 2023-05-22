import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AnswerSubmitCommandComponent } from './list/answer-submit-command.component';
import { AnswerSubmitCommandDetailComponent } from './detail/answer-submit-command-detail.component';
import { AnswerSubmitCommandUpdateComponent } from './update/answer-submit-command-update.component';
import { AnswerSubmitCommandDeleteDialogComponent } from './delete/answer-submit-command-delete-dialog.component';
import { AnswerSubmitCommandRoutingModule } from './route/answer-submit-command-routing.module';

@NgModule({
  imports: [SharedModule, AnswerSubmitCommandRoutingModule],
  declarations: [
    AnswerSubmitCommandComponent,
    AnswerSubmitCommandDetailComponent,
    AnswerSubmitCommandUpdateComponent,
    AnswerSubmitCommandDeleteDialogComponent,
  ],
})
export class AnswerSubmitCommandModule {}
