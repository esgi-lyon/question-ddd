import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CreatedQuestionEventComponent } from './list/created-question-event.component';
import { CreatedQuestionEventDetailComponent } from './detail/created-question-event-detail.component';
import { CreatedQuestionEventRoutingModule } from './route/created-question-event-routing.module';

@NgModule({
  imports: [SharedModule, CreatedQuestionEventRoutingModule],
  declarations: [CreatedQuestionEventComponent, CreatedQuestionEventDetailComponent],
})
export class CreatedQuestionEventModule {}
