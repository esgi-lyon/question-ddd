import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISendQuestionByTagsPreferencesCommand } from '../send-question-by-tags-preferences-command.model';
import { SendQuestionByTagsPreferencesCommandService } from '../service/send-question-by-tags-preferences-command.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './send-question-by-tags-preferences-command-delete-dialog.component.html',
})
export class SendQuestionByTagsPreferencesCommandDeleteDialogComponent {
  sendQuestionByTagsPreferencesCommand?: ISendQuestionByTagsPreferencesCommand;

  constructor(
    protected sendQuestionByTagsPreferencesCommandService: SendQuestionByTagsPreferencesCommandService,
    protected activeModal: NgbActiveModal
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sendQuestionByTagsPreferencesCommandService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
