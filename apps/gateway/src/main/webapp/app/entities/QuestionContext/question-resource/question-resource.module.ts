import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { QuestionResourceComponent } from './list/question-resource.component';
import { QuestionResourceDetailComponent } from './detail/question-resource-detail.component';
import { QuestionResourceUpdateComponent } from './update/question-resource-update.component';
import { QuestionResourceDeleteDialogComponent } from './delete/question-resource-delete-dialog.component';
import { QuestionResourceRoutingModule } from './route/question-resource-routing.module';

@NgModule({
  imports: [SharedModule, QuestionResourceRoutingModule],
  declarations: [
    QuestionResourceComponent,
    QuestionResourceDetailComponent,
    QuestionResourceUpdateComponent,
    QuestionResourceDeleteDialogComponent,
  ],
})
export class QuestionContextQuestionResourceModule {}
