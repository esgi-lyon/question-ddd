import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { TagChoicesListCommandComponent } from './list/tag-choices-list-command.component';
import { TagChoicesListCommandDetailComponent } from './detail/tag-choices-list-command-detail.component';
import { TagChoicesListCommandUpdateComponent } from './update/tag-choices-list-command-update.component';
import { TagChoicesListCommandDeleteDialogComponent } from './delete/tag-choices-list-command-delete-dialog.component';
import { TagChoicesListCommandRoutingModule } from './route/tag-choices-list-command-routing.module';

@NgModule({
  imports: [SharedModule, TagChoicesListCommandRoutingModule],
  declarations: [
    TagChoicesListCommandComponent,
    TagChoicesListCommandDetailComponent,
    TagChoicesListCommandUpdateComponent,
    TagChoicesListCommandDeleteDialogComponent,
  ],
})
export class TagChoicesListCommandModule {}
