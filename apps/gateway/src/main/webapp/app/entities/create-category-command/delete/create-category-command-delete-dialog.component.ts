import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ICreateCategoryCommand } from '../create-category-command.model';
import { CreateCategoryCommandService } from '../service/create-category-command.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './create-category-command-delete-dialog.component.html',
})
export class CreateCategoryCommandDeleteDialogComponent {
  createCategoryCommand?: ICreateCategoryCommand;

  constructor(protected createCategoryCommandService: CreateCategoryCommandService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.createCategoryCommandService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
