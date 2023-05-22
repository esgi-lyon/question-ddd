import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CreateResourceCommandComponent } from './list/create-resource-command.component';
import { CreateResourceCommandDetailComponent } from './detail/create-resource-command-detail.component';
import { CreateResourceCommandUpdateComponent } from './update/create-resource-command-update.component';
import { CreateResourceCommandDeleteDialogComponent } from './delete/create-resource-command-delete-dialog.component';
import { CreateResourceCommandRoutingModule } from './route/create-resource-command-routing.module';

@NgModule({
  imports: [SharedModule, CreateResourceCommandRoutingModule],
  declarations: [
    CreateResourceCommandComponent,
    CreateResourceCommandDetailComponent,
    CreateResourceCommandUpdateComponent,
    CreateResourceCommandDeleteDialogComponent,
  ],
})
export class CreateResourceCommandModule {}
