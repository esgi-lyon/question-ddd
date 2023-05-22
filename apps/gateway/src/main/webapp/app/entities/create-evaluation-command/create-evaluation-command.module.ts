import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CreateEvaluationCommandComponent } from './list/create-evaluation-command.component';
import { CreateEvaluationCommandDetailComponent } from './detail/create-evaluation-command-detail.component';
import { CreateEvaluationCommandUpdateComponent } from './update/create-evaluation-command-update.component';
import { CreateEvaluationCommandDeleteDialogComponent } from './delete/create-evaluation-command-delete-dialog.component';
import { CreateEvaluationCommandRoutingModule } from './route/create-evaluation-command-routing.module';

@NgModule({
  imports: [SharedModule, CreateEvaluationCommandRoutingModule],
  declarations: [
    CreateEvaluationCommandComponent,
    CreateEvaluationCommandDetailComponent,
    CreateEvaluationCommandUpdateComponent,
    CreateEvaluationCommandDeleteDialogComponent,
  ],
})
export class CreateEvaluationCommandModule {}
