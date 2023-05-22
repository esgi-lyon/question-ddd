import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { QuestionResourceTagInfosComponent } from './list/question-resource-tag-infos.component';
import { QuestionResourceTagInfosDetailComponent } from './detail/question-resource-tag-infos-detail.component';
import { QuestionResourceTagInfosRoutingModule } from './route/question-resource-tag-infos-routing.module';

@NgModule({
  imports: [SharedModule, QuestionResourceTagInfosRoutingModule],
  declarations: [QuestionResourceTagInfosComponent, QuestionResourceTagInfosDetailComponent],
})
export class QuestionResourceTagInfosModule {}
