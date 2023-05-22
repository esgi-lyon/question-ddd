import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CreateQuestionCommandComponent } from './list/create-question-command.component';
import { CreateQuestionCommandDetailComponent } from './detail/create-question-command-detail.component';
import { CreateQuestionCommandUpdateComponent } from './update/create-question-command-update.component';
import { CreateQuestionCommandDeleteDialogComponent } from './delete/create-question-command-delete-dialog.component';
import { CreateQuestionCommandRoutingModule } from './route/create-question-command-routing.module';

@NgModule({
  imports: [SharedModule, CreateQuestionCommandRoutingModule],
  declarations: [
    CreateQuestionCommandComponent,
    CreateQuestionCommandDetailComponent,
    CreateQuestionCommandUpdateComponent,
    CreateQuestionCommandDeleteDialogComponent,
  ],
})
export class CreateQuestionCommandModule {}
