import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { EvaluationIdComponent } from './list/evaluation-id.component';
import { EvaluationIdDetailComponent } from './detail/evaluation-id-detail.component';
import { EvaluationIdRoutingModule } from './route/evaluation-id-routing.module';

@NgModule({
  imports: [SharedModule, EvaluationIdRoutingModule],
  declarations: [EvaluationIdComponent, EvaluationIdDetailComponent],
})
export class EvaluationIdModule {}
