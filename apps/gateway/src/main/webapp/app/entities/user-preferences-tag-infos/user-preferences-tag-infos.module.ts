import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { UserPreferencesTagInfosComponent } from './list/user-preferences-tag-infos.component';
import { UserPreferencesTagInfosDetailComponent } from './detail/user-preferences-tag-infos-detail.component';
import { UserPreferencesTagInfosRoutingModule } from './route/user-preferences-tag-infos-routing.module';

@NgModule({
  imports: [SharedModule, UserPreferencesTagInfosRoutingModule],
  declarations: [UserPreferencesTagInfosComponent, UserPreferencesTagInfosDetailComponent],
})
export class UserPreferencesTagInfosModule {}
