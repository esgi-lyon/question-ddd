import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { EvaluationQuestionComponent } from './list/evaluation-question.component';
import { EvaluationQuestionDetailComponent } from './detail/evaluation-question-detail.component';
import { EvaluationQuestionRoutingModule } from './route/evaluation-question-routing.module';

@NgModule({
  imports: [SharedModule, EvaluationQuestionRoutingModule],
  declarations: [EvaluationQuestionComponent, EvaluationQuestionDetailComponent],
})
export class EvaluationQuestionModule {}
