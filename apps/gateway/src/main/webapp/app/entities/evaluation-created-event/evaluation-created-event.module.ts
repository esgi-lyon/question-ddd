import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { EvaluationCreatedEventComponent } from './list/evaluation-created-event.component';
import { EvaluationCreatedEventDetailComponent } from './detail/evaluation-created-event-detail.component';
import { EvaluationCreatedEventRoutingModule } from './route/evaluation-created-event-routing.module';

@NgModule({
  imports: [SharedModule, EvaluationCreatedEventRoutingModule],
  declarations: [EvaluationCreatedEventComponent, EvaluationCreatedEventDetailComponent],
})
export class EvaluationCreatedEventModule {}
