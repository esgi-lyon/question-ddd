import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ResourceRefusedAssociationEventComponent } from './list/resource-refused-association-event.component';
import { ResourceRefusedAssociationEventDetailComponent } from './detail/resource-refused-association-event-detail.component';
import { ResourceRefusedAssociationEventRoutingModule } from './route/resource-refused-association-event-routing.module';

@NgModule({
  imports: [SharedModule, ResourceRefusedAssociationEventRoutingModule],
  declarations: [ResourceRefusedAssociationEventComponent, ResourceRefusedAssociationEventDetailComponent],
})
export class ResourceRefusedAssociationEventModule {}
