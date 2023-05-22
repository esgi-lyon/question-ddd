import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CategoryCreatedEventComponent } from './list/category-created-event.component';
import { CategoryCreatedEventDetailComponent } from './detail/category-created-event-detail.component';
import { CategoryCreatedEventRoutingModule } from './route/category-created-event-routing.module';

@NgModule({
  imports: [SharedModule, CategoryCreatedEventRoutingModule],
  declarations: [CategoryCreatedEventComponent, CategoryCreatedEventDetailComponent],
})
export class CategoryCreatedEventModule {}
