import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { QuestionSentTagInfosComponent } from './list/question-sent-tag-infos.component';
import { QuestionSentTagInfosDetailComponent } from './detail/question-sent-tag-infos-detail.component';
import { QuestionSentTagInfosRoutingModule } from './route/question-sent-tag-infos-routing.module';

@NgModule({
  imports: [SharedModule, QuestionSentTagInfosRoutingModule],
  declarations: [QuestionSentTagInfosComponent, QuestionSentTagInfosDetailComponent],
})
export class QuestionSentTagInfosModule {}
