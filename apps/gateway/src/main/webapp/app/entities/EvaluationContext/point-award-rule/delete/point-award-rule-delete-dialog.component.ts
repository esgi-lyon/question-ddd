import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IPointAwardRule } from '../point-award-rule.model';
import { PointAwardRuleService } from '../service/point-award-rule.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './point-award-rule-delete-dialog.component.html',
})
export class PointAwardRuleDeleteDialogComponent {
  pointAwardRule?: IPointAwardRule;

  constructor(protected pointAwardRuleService: PointAwardRuleService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.pointAwardRuleService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
