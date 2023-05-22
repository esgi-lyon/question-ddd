import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IRejectResourceTagCommand } from '../reject-resource-tag-command.model';
import { RejectResourceTagCommandService } from '../service/reject-resource-tag-command.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './reject-resource-tag-command-delete-dialog.component.html',
})
export class RejectResourceTagCommandDeleteDialogComponent {
  rejectResourceTagCommand?: IRejectResourceTagCommand;

  constructor(protected rejectResourceTagCommandService: RejectResourceTagCommandService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.rejectResourceTagCommandService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
