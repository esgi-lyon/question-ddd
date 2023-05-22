import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ResourceAcceptedAssociationEventComponent } from './list/resource-accepted-association-event.component';
import { ResourceAcceptedAssociationEventDetailComponent } from './detail/resource-accepted-association-event-detail.component';
import { ResourceAcceptedAssociationEventRoutingModule } from './route/resource-accepted-association-event-routing.module';

@NgModule({
  imports: [SharedModule, ResourceAcceptedAssociationEventRoutingModule],
  declarations: [ResourceAcceptedAssociationEventComponent, ResourceAcceptedAssociationEventDetailComponent],
})
export class ResourceAcceptedAssociationEventModule {}
