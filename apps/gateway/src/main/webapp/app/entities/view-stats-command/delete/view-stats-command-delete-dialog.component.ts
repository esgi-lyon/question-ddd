import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IViewStatsCommand } from '../view-stats-command.model';
import { ViewStatsCommandService } from '../service/view-stats-command.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './view-stats-command-delete-dialog.component.html',
})
export class ViewStatsCommandDeleteDialogComponent {
  viewStatsCommand?: IViewStatsCommand;

  constructor(protected viewStatsCommandService: ViewStatsCommandService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.viewStatsCommandService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
