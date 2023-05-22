import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AnswerSubmittedEventComponent } from './list/answer-submitted-event.component';
import { AnswerSubmittedEventDetailComponent } from './detail/answer-submitted-event-detail.component';
import { AnswerSubmittedEventRoutingModule } from './route/answer-submitted-event-routing.module';

@NgModule({
  imports: [SharedModule, AnswerSubmittedEventRoutingModule],
  declarations: [AnswerSubmittedEventComponent, AnswerSubmittedEventDetailComponent],
})
export class AnswerSubmittedEventModule {}
