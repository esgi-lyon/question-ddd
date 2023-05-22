import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IAnswerSubmitCommand } from '../answer-submit-command.model';
import { AnswerSubmitCommandService } from '../service/answer-submit-command.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './answer-submit-command-delete-dialog.component.html',
})
export class AnswerSubmitCommandDeleteDialogComponent {
  answerSubmitCommand?: IAnswerSubmitCommand;

  constructor(protected answerSubmitCommandService: AnswerSubmitCommandService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.answerSubmitCommandService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
