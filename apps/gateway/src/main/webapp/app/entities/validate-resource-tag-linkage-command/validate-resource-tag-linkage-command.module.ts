import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ValidateResourceTagLinkageCommandComponent } from './list/validate-resource-tag-linkage-command.component';
import { ValidateResourceTagLinkageCommandDetailComponent } from './detail/validate-resource-tag-linkage-command-detail.component';
import { ValidateResourceTagLinkageCommandUpdateComponent } from './update/validate-resource-tag-linkage-command-update.component';
import { ValidateResourceTagLinkageCommandDeleteDialogComponent } from './delete/validate-resource-tag-linkage-command-delete-dialog.component';
import { ValidateResourceTagLinkageCommandRoutingModule } from './route/validate-resource-tag-linkage-command-routing.module';

@NgModule({
  imports: [SharedModule, ValidateResourceTagLinkageCommandRoutingModule],
  declarations: [
    ValidateResourceTagLinkageCommandComponent,
    ValidateResourceTagLinkageCommandDetailComponent,
    ValidateResourceTagLinkageCommandUpdateComponent,
    ValidateResourceTagLinkageCommandDeleteDialogComponent,
  ],
})
export class ValidateResourceTagLinkageCommandModule {}
