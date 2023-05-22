import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { QuestionSentTagIdComponent } from './list/question-sent-tag-id.component';
import { QuestionSentTagIdDetailComponent } from './detail/question-sent-tag-id-detail.component';
import { QuestionSentTagIdRoutingModule } from './route/question-sent-tag-id-routing.module';

@NgModule({
  imports: [SharedModule, QuestionSentTagIdRoutingModule],
  declarations: [QuestionSentTagIdComponent, QuestionSentTagIdDetailComponent],
})
export class QuestionSentTagIdModule {}
