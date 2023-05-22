import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ICheckAnswerCommand } from '../check-answer-command.model';
import { CheckAnswerCommandService } from '../service/check-answer-command.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './check-answer-command-delete-dialog.component.html',
})
export class CheckAnswerCommandDeleteDialogComponent {
  checkAnswerCommand?: ICheckAnswerCommand;

  constructor(protected checkAnswerCommandService: CheckAnswerCommandService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.checkAnswerCommandService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
