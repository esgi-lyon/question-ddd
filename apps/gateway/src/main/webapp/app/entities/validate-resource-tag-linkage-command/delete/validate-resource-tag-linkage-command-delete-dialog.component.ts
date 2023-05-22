import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IValidateResourceTagLinkageCommand } from '../validate-resource-tag-linkage-command.model';
import { ValidateResourceTagLinkageCommandService } from '../service/validate-resource-tag-linkage-command.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './validate-resource-tag-linkage-command-delete-dialog.component.html',
})
export class ValidateResourceTagLinkageCommandDeleteDialogComponent {
  validateResourceTagLinkageCommand?: IValidateResourceTagLinkageCommand;

  constructor(
    protected validateResourceTagLinkageCommandService: ValidateResourceTagLinkageCommandService,
    protected activeModal: NgbActiveModal
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.validateResourceTagLinkageCommandService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
