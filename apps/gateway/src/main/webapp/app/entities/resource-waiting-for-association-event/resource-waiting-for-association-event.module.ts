import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ResourceWaitingForAssociationEventComponent } from './list/resource-waiting-for-association-event.component';
import { ResourceWaitingForAssociationEventDetailComponent } from './detail/resource-waiting-for-association-event-detail.component';
import { ResourceWaitingForAssociationEventRoutingModule } from './route/resource-waiting-for-association-event-routing.module';

@NgModule({
  imports: [SharedModule, ResourceWaitingForAssociationEventRoutingModule],
  declarations: [ResourceWaitingForAssociationEventComponent, ResourceWaitingForAssociationEventDetailComponent],
})
export class ResourceWaitingForAssociationEventModule {}
