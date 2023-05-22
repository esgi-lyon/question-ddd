import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { PointAwardRuleComponent } from './list/point-award-rule.component';
import { PointAwardRuleDetailComponent } from './detail/point-award-rule-detail.component';
import { PointAwardRuleUpdateComponent } from './update/point-award-rule-update.component';
import { PointAwardRuleDeleteDialogComponent } from './delete/point-award-rule-delete-dialog.component';
import { PointAwardRuleRoutingModule } from './route/point-award-rule-routing.module';

@NgModule({
  imports: [SharedModule, PointAwardRuleRoutingModule],
  declarations: [
    PointAwardRuleComponent,
    PointAwardRuleDetailComponent,
    PointAwardRuleUpdateComponent,
    PointAwardRuleDeleteDialogComponent,
  ],
})
export class EvaluationContextPointAwardRuleModule {}
