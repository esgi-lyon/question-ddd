import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { TagStatsViewedEventComponent } from './list/tag-stats-viewed-event.component';
import { TagStatsViewedEventDetailComponent } from './detail/tag-stats-viewed-event-detail.component';
import { TagStatsViewedEventRoutingModule } from './route/tag-stats-viewed-event-routing.module';

@NgModule({
  imports: [SharedModule, TagStatsViewedEventRoutingModule],
  declarations: [TagStatsViewedEventComponent, TagStatsViewedEventDetailComponent],
})
export class TagStatsViewedEventModule {}
