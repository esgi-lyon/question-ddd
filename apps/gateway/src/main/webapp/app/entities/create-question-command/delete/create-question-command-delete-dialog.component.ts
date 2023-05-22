import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ICreateQuestionCommand } from '../create-question-command.model';
import { CreateQuestionCommandService } from '../service/create-question-command.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './create-question-command-delete-dialog.component.html',
})
export class CreateQuestionCommandDeleteDialogComponent {
  createQuestionCommand?: ICreateQuestionCommand;

  constructor(protected createQuestionCommandService: CreateQuestionCommandService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.createQuestionCommandService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
