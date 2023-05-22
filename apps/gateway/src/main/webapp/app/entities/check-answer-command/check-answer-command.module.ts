import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CheckAnswerCommandComponent } from './list/check-answer-command.component';
import { CheckAnswerCommandDetailComponent } from './detail/check-answer-command-detail.component';
import { CheckAnswerCommandUpdateComponent } from './update/check-answer-command-update.component';
import { CheckAnswerCommandDeleteDialogComponent } from './delete/check-answer-command-delete-dialog.component';
import { CheckAnswerCommandRoutingModule } from './route/check-answer-command-routing.module';

@NgModule({
  imports: [SharedModule, CheckAnswerCommandRoutingModule],
  declarations: [
    CheckAnswerCommandComponent,
    CheckAnswerCommandDetailComponent,
    CheckAnswerCommandUpdateComponent,
    CheckAnswerCommandDeleteDialogComponent,
  ],
})
export class CheckAnswerCommandModule {}
