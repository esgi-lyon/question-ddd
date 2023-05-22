import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { StatisticSubjectUserComponent } from './list/statistic-subject-user.component';
import { StatisticSubjectUserDetailComponent } from './detail/statistic-subject-user-detail.component';
import { StatisticSubjectUserRoutingModule } from './route/statistic-subject-user-routing.module';

@NgModule({
  imports: [SharedModule, StatisticSubjectUserRoutingModule],
  declarations: [StatisticSubjectUserComponent, StatisticSubjectUserDetailComponent],
})
export class StatisticSubjectUserModule {}
