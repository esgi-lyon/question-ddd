import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { EvaluationTagComponent } from './list/evaluation-tag.component';
import { EvaluationTagDetailComponent } from './detail/evaluation-tag-detail.component';
import { EvaluationTagRoutingModule } from './route/evaluation-tag-routing.module';

@NgModule({
  imports: [SharedModule, EvaluationTagRoutingModule],
  declarations: [EvaluationTagComponent, EvaluationTagDetailComponent],
})
export class EvaluationTagModule {}
