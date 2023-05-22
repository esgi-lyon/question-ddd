import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ICreateTagCommand } from '../create-tag-command.model';
import { CreateTagCommandService } from '../service/create-tag-command.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './create-tag-command-delete-dialog.component.html',
})
export class CreateTagCommandDeleteDialogComponent {
  createTagCommand?: ICreateTagCommand;

  constructor(protected createTagCommandService: CreateTagCommandService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.createTagCommandService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
