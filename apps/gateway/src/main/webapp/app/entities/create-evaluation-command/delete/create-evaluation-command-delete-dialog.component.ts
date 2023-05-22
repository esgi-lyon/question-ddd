import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ICreateEvaluationCommand } from '../create-evaluation-command.model';
import { CreateEvaluationCommandService } from '../service/create-evaluation-command.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './create-evaluation-command-delete-dialog.component.html',
})
export class CreateEvaluationCommandDeleteDialogComponent {
  createEvaluationCommand?: ICreateEvaluationCommand;

  constructor(protected createEvaluationCommandService: CreateEvaluationCommandService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.createEvaluationCommandService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
