import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AnsweringUserComponent } from './list/answering-user.component';
import { AnsweringUserDetailComponent } from './detail/answering-user-detail.component';
import { AnsweringUserRoutingModule } from './route/answering-user-routing.module';

@NgModule({
  imports: [SharedModule, AnsweringUserRoutingModule],
  declarations: [AnsweringUserComponent, AnsweringUserDetailComponent],
})
export class AnsweringUserModule {}
