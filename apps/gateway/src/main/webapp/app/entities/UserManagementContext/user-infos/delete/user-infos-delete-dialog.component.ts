import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserInfos } from '../user-infos.model';
import { UserInfosService } from '../service/user-infos.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './user-infos-delete-dialog.component.html',
})
export class UserInfosDeleteDialogComponent {
  userInfos?: IUserInfos;

  constructor(protected userInfosService: UserInfosService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.userInfosService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
