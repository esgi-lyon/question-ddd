import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { StatisticSubjectQuestionComponent } from './list/statistic-subject-question.component';
import { StatisticSubjectQuestionDetailComponent } from './detail/statistic-subject-question-detail.component';
import { StatisticSubjectQuestionRoutingModule } from './route/statistic-subject-question-routing.module';

@NgModule({
  imports: [SharedModule, StatisticSubjectQuestionRoutingModule],
  declarations: [StatisticSubjectQuestionComponent, StatisticSubjectQuestionDetailComponent],
})
export class StatisticSubjectQuestionModule {}
