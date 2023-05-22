import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { TagInfosComponent } from './list/tag-infos.component';
import { TagInfosDetailComponent } from './detail/tag-infos-detail.component';
import { TagInfosRoutingModule } from './route/tag-infos-routing.module';

@NgModule({
  imports: [SharedModule, TagInfosRoutingModule],
  declarations: [TagInfosComponent, TagInfosDetailComponent],
})
export class TagInfosModule {}
