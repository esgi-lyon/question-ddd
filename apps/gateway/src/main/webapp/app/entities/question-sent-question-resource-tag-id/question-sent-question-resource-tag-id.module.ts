import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { QuestionSentQuestionResourceTagIdComponent } from './list/question-sent-question-resource-tag-id.component';
import { QuestionSentQuestionResourceTagIdDetailComponent } from './detail/question-sent-question-resource-tag-id-detail.component';
import { QuestionSentQuestionResourceTagIdRoutingModule } from './route/question-sent-question-resource-tag-id-routing.module';

@NgModule({
  imports: [SharedModule, QuestionSentQuestionResourceTagIdRoutingModule],
  declarations: [QuestionSentQuestionResourceTagIdComponent, QuestionSentQuestionResourceTagIdDetailComponent],
})
export class QuestionSentQuestionResourceTagIdModule {}
