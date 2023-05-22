import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { TagChoicesListedEventComponent } from './list/tag-choices-listed-event.component';
import { TagChoicesListedEventDetailComponent } from './detail/tag-choices-listed-event-detail.component';
import { TagChoicesListedEventRoutingModule } from './route/tag-choices-listed-event-routing.module';

@NgModule({
  imports: [SharedModule, TagChoicesListedEventRoutingModule],
  declarations: [TagChoicesListedEventComponent, TagChoicesListedEventDetailComponent],
})
export class TagChoicesListedEventModule {}
