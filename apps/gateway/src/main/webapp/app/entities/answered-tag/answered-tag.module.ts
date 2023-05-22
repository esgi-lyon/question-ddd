import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AnsweredTagComponent } from './list/answered-tag.component';
import { AnsweredTagDetailComponent } from './detail/answered-tag-detail.component';
import { AnsweredTagRoutingModule } from './route/answered-tag-routing.module';

@NgModule({
  imports: [SharedModule, AnsweredTagRoutingModule],
  declarations: [AnsweredTagComponent, AnsweredTagDetailComponent],
})
export class AnsweredTagModule {}
