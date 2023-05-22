import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { QuestionIdComponent } from './list/question-id.component';
import { QuestionIdDetailComponent } from './detail/question-id-detail.component';
import { QuestionIdRoutingModule } from './route/question-id-routing.module';

@NgModule({
  imports: [SharedModule, QuestionIdRoutingModule],
  declarations: [QuestionIdComponent, QuestionIdDetailComponent],
})
export class QuestionIdModule {}
