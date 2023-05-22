import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CreateTagCommandComponent } from './list/create-tag-command.component';
import { CreateTagCommandDetailComponent } from './detail/create-tag-command-detail.component';
import { CreateTagCommandUpdateComponent } from './update/create-tag-command-update.component';
import { CreateTagCommandDeleteDialogComponent } from './delete/create-tag-command-delete-dialog.component';
import { CreateTagCommandRoutingModule } from './route/create-tag-command-routing.module';

@NgModule({
  imports: [SharedModule, CreateTagCommandRoutingModule],
  declarations: [
    CreateTagCommandComponent,
    CreateTagCommandDetailComponent,
    CreateTagCommandUpdateComponent,
    CreateTagCommandDeleteDialogComponent,
  ],
})
export class CreateTagCommandModule {}
