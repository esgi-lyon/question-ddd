import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CreateCategoryCommandComponent } from './list/create-category-command.component';
import { CreateCategoryCommandDetailComponent } from './detail/create-category-command-detail.component';
import { CreateCategoryCommandUpdateComponent } from './update/create-category-command-update.component';
import { CreateCategoryCommandDeleteDialogComponent } from './delete/create-category-command-delete-dialog.component';
import { CreateCategoryCommandRoutingModule } from './route/create-category-command-routing.module';

@NgModule({
  imports: [SharedModule, CreateCategoryCommandRoutingModule],
  declarations: [
    CreateCategoryCommandComponent,
    CreateCategoryCommandDetailComponent,
    CreateCategoryCommandUpdateComponent,
    CreateCategoryCommandDeleteDialogComponent,
  ],
})
export class CreateCategoryCommandModule {}
