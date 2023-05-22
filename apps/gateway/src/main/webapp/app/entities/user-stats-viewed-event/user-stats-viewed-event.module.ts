import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { UserStatsViewedEventComponent } from './list/user-stats-viewed-event.component';
import { UserStatsViewedEventDetailComponent } from './detail/user-stats-viewed-event-detail.component';
import { UserStatsViewedEventRoutingModule } from './route/user-stats-viewed-event-routing.module';

@NgModule({
  imports: [SharedModule, UserStatsViewedEventRoutingModule],
  declarations: [UserStatsViewedEventComponent, UserStatsViewedEventDetailComponent],
})
export class UserStatsViewedEventModule {}
