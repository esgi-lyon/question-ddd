import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { UserWithPreferencesIdComponent } from './list/user-with-preferences-id.component';
import { UserWithPreferencesIdDetailComponent } from './detail/user-with-preferences-id-detail.component';
import { UserWithPreferencesIdRoutingModule } from './route/user-with-preferences-id-routing.module';

@NgModule({
  imports: [SharedModule, UserWithPreferencesIdRoutingModule],
  declarations: [UserWithPreferencesIdComponent, UserWithPreferencesIdDetailComponent],
})
export class UserWithPreferencesIdModule {}
