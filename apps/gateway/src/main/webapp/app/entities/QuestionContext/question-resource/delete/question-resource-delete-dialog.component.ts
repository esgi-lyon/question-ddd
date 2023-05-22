import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IQuestionResource } from '../question-resource.model';
import { QuestionResourceService } from '../service/question-resource.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './question-resource-delete-dialog.component.html',
})
export class QuestionResourceDeleteDialogComponent {
  questionResource?: IQuestionResource;

  constructor(protected questionResourceService: QuestionResourceService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.questionResourceService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
