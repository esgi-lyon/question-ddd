import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { RejectResourceTagCommandComponent } from './list/reject-resource-tag-command.component';
import { RejectResourceTagCommandDetailComponent } from './detail/reject-resource-tag-command-detail.component';
import { RejectResourceTagCommandUpdateComponent } from './update/reject-resource-tag-command-update.component';
import { RejectResourceTagCommandDeleteDialogComponent } from './delete/reject-resource-tag-command-delete-dialog.component';
import { RejectResourceTagCommandRoutingModule } from './route/reject-resource-tag-command-routing.module';

@NgModule({
  imports: [SharedModule, RejectResourceTagCommandRoutingModule],
  declarations: [
    RejectResourceTagCommandComponent,
    RejectResourceTagCommandDetailComponent,
    RejectResourceTagCommandUpdateComponent,
    RejectResourceTagCommandDeleteDialogComponent,
  ],
})
export class RejectResourceTagCommandModule {}
