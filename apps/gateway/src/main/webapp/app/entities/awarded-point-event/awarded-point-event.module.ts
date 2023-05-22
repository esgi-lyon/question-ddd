import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AwardedPointEventComponent } from './list/awarded-point-event.component';
import { AwardedPointEventDetailComponent } from './detail/awarded-point-event-detail.component';
import { AwardedPointEventRoutingModule } from './route/awarded-point-event-routing.module';

@NgModule({
  imports: [SharedModule, AwardedPointEventRoutingModule],
  declarations: [AwardedPointEventComponent, AwardedPointEventDetailComponent],
})
export class AwardedPointEventModule {}
