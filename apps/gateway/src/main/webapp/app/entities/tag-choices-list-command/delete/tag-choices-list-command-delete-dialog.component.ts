import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITagChoicesListCommand } from '../tag-choices-list-command.model';
import { TagChoicesListCommandService } from '../service/tag-choices-list-command.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './tag-choices-list-command-delete-dialog.component.html',
})
export class TagChoicesListCommandDeleteDialogComponent {
  tagChoicesListCommand?: ITagChoicesListCommand;

  constructor(protected tagChoicesListCommandService: TagChoicesListCommandService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tagChoicesListCommandService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
