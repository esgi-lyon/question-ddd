import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IAwardPointForEvaluationCommand } from '../award-point-for-evaluation-command.model';
import { AwardPointForEvaluationCommandService } from '../service/award-point-for-evaluation-command.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './award-point-for-evaluation-command-delete-dialog.component.html',
})
export class AwardPointForEvaluationCommandDeleteDialogComponent {
  awardPointForEvaluationCommand?: IAwardPointForEvaluationCommand;

  constructor(
    protected awardPointForEvaluationCommandService: AwardPointForEvaluationCommandService,
    protected activeModal: NgbActiveModal
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.awardPointForEvaluationCommandService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
