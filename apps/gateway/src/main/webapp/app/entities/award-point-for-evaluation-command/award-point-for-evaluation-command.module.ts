import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AwardPointForEvaluationCommandComponent } from './list/award-point-for-evaluation-command.component';
import { AwardPointForEvaluationCommandDetailComponent } from './detail/award-point-for-evaluation-command-detail.component';
import { AwardPointForEvaluationCommandUpdateComponent } from './update/award-point-for-evaluation-command-update.component';
import { AwardPointForEvaluationCommandDeleteDialogComponent } from './delete/award-point-for-evaluation-command-delete-dialog.component';
import { AwardPointForEvaluationCommandRoutingModule } from './route/award-point-for-evaluation-command-routing.module';

@NgModule({
  imports: [SharedModule, AwardPointForEvaluationCommandRoutingModule],
  declarations: [
    AwardPointForEvaluationCommandComponent,
    AwardPointForEvaluationCommandDetailComponent,
    AwardPointForEvaluationCommandUpdateComponent,
    AwardPointForEvaluationCommandDeleteDialogComponent,
  ],
})
export class AwardPointForEvaluationCommandModule {}
