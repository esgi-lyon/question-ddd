import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { QuestionSentComponent } from './list/question-sent.component';
import { QuestionSentDetailComponent } from './detail/question-sent-detail.component';
import { QuestionSentUpdateComponent } from './update/question-sent-update.component';
import { QuestionSentDeleteDialogComponent } from './delete/question-sent-delete-dialog.component';
import { QuestionSentRoutingModule } from './route/question-sent-routing.module';

@NgModule({
  imports: [SharedModule, QuestionSentRoutingModule],
  declarations: [QuestionSentComponent, QuestionSentDetailComponent, QuestionSentUpdateComponent, QuestionSentDeleteDialogComponent],
})
export class SendQuestionContextQuestionSentModule {}
