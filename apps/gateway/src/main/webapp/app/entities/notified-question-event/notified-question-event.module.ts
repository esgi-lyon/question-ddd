import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { NotifiedQuestionEventComponent } from './list/notified-question-event.component';
import { NotifiedQuestionEventDetailComponent } from './detail/notified-question-event-detail.component';
import { NotifiedQuestionEventRoutingModule } from './route/notified-question-event-routing.module';

@NgModule({
  imports: [SharedModule, NotifiedQuestionEventRoutingModule],
  declarations: [NotifiedQuestionEventComponent, NotifiedQuestionEventDetailComponent],
})
export class NotifiedQuestionEventModule {}
