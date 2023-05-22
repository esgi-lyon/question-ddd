import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CategoryIdComponent } from './list/category-id.component';
import { CategoryIdDetailComponent } from './detail/category-id-detail.component';
import { CategoryIdRoutingModule } from './route/category-id-routing.module';

@NgModule({
  imports: [SharedModule, CategoryIdRoutingModule],
  declarations: [CategoryIdComponent, CategoryIdDetailComponent],
})
export class CategoryIdModule {}
