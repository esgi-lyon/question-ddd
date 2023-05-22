import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { QuestionComponent } from './list/question.component';
import { QuestionDetailComponent } from './detail/question-detail.component';
import { QuestionRoutingModule } from './route/question-routing.module';

@NgModule({
  imports: [SharedModule, QuestionRoutingModule],
  declarations: [QuestionComponent, QuestionDetailComponent],
})
export class QuestionModule {}
