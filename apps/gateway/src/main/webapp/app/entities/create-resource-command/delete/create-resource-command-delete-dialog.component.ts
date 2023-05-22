import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ICreateResourceCommand } from '../create-resource-command.model';
import { CreateResourceCommandService } from '../service/create-resource-command.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './create-resource-command-delete-dialog.component.html',
})
export class CreateResourceCommandDeleteDialogComponent {
  createResourceCommand?: ICreateResourceCommand;

  constructor(protected createResourceCommandService: CreateResourceCommandService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.createResourceCommandService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
