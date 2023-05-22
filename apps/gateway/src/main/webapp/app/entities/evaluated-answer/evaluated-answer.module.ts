import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { EvaluatedAnswerComponent } from './list/evaluated-answer.component';
import { EvaluatedAnswerDetailComponent } from './detail/evaluated-answer-detail.component';
import { EvaluatedAnswerRoutingModule } from './route/evaluated-answer-routing.module';

@NgModule({
  imports: [SharedModule, EvaluatedAnswerRoutingModule],
  declarations: [EvaluatedAnswerComponent, EvaluatedAnswerDetailComponent],
})
export class EvaluatedAnswerModule {}
