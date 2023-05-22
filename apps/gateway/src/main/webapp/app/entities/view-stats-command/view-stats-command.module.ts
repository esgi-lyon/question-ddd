import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ViewStatsCommandComponent } from './list/view-stats-command.component';
import { ViewStatsCommandDetailComponent } from './detail/view-stats-command-detail.component';
import { ViewStatsCommandUpdateComponent } from './update/view-stats-command-update.component';
import { ViewStatsCommandDeleteDialogComponent } from './delete/view-stats-command-delete-dialog.component';
import { ViewStatsCommandRoutingModule } from './route/view-stats-command-routing.module';

@NgModule({
  imports: [SharedModule, ViewStatsCommandRoutingModule],
  declarations: [
    ViewStatsCommandComponent,
    ViewStatsCommandDetailComponent,
    ViewStatsCommandUpdateComponent,
    ViewStatsCommandDeleteDialogComponent,
  ],
})
export class ViewStatsCommandModule {}
