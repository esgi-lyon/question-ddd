import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { TagCreatedEventComponent } from './list/tag-created-event.component';
import { TagCreatedEventDetailComponent } from './detail/tag-created-event-detail.component';
import { TagCreatedEventRoutingModule } from './route/tag-created-event-routing.module';

@NgModule({
  imports: [SharedModule, TagCreatedEventRoutingModule],
  declarations: [TagCreatedEventComponent, TagCreatedEventDetailComponent],
})
export class TagCreatedEventModule {}
