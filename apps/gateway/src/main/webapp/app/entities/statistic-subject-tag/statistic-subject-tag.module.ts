import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { StatisticSubjectTagComponent } from './list/statistic-subject-tag.component';
import { StatisticSubjectTagDetailComponent } from './detail/statistic-subject-tag-detail.component';
import { StatisticSubjectTagRoutingModule } from './route/statistic-subject-tag-routing.module';

@NgModule({
  imports: [SharedModule, StatisticSubjectTagRoutingModule],
  declarations: [StatisticSubjectTagComponent, StatisticSubjectTagDetailComponent],
})
export class StatisticSubjectTagModule {}
