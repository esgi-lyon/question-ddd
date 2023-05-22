import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AnswerCheckedEventComponent } from './list/answer-checked-event.component';
import { AnswerCheckedEventDetailComponent } from './detail/answer-checked-event-detail.component';
import { AnswerCheckedEventRoutingModule } from './route/answer-checked-event-routing.module';

@NgModule({
  imports: [SharedModule, AnswerCheckedEventRoutingModule],
  declarations: [AnswerCheckedEventComponent, AnswerCheckedEventDetailComponent],
})
export class AnswerCheckedEventModule {}
