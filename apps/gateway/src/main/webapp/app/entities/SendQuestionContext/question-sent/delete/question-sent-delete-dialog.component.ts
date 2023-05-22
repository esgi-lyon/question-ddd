import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IQuestionSent } from '../question-sent.model';
import { QuestionSentService } from '../service/question-sent.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './question-sent-delete-dialog.component.html',
})
export class QuestionSentDeleteDialogComponent {
  questionSent?: IQuestionSent;

  constructor(protected questionSentService: QuestionSentService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.questionSentService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
