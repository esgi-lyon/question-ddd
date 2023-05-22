import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEvaluation } from '../evaluation.model';
import { EvaluationService } from '../service/evaluation.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './evaluation-delete-dialog.component.html',
})
export class EvaluationDeleteDialogComponent {
  evaluation?: IEvaluation;

  constructor(protected evaluationService: EvaluationService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.evaluationService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
