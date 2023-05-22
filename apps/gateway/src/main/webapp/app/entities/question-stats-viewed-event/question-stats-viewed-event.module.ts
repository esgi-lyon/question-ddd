import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { QuestionStatsViewedEventComponent } from './list/question-stats-viewed-event.component';
import { QuestionStatsViewedEventDetailComponent } from './detail/question-stats-viewed-event-detail.component';
import { QuestionStatsViewedEventRoutingModule } from './route/question-stats-viewed-event-routing.module';

@NgModule({
  imports: [SharedModule, QuestionStatsViewedEventRoutingModule],
  declarations: [QuestionStatsViewedEventComponent, QuestionStatsViewedEventDetailComponent],
})
export class QuestionStatsViewedEventModule {}
