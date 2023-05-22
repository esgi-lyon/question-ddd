import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IValidateResourceTagLinkageCommand } from '../validate-resource-tag-linkage-command.model';

@Component({
  selector: 'jhi-validate-resource-tag-linkage-command-detail',
  templateUrl: './validate-resource-tag-linkage-command-detail.component.html',
})
export class ValidateResourceTagLinkageCommandDetailComponent implements OnInit {
  validateResourceTagLinkageCommand: IValidateResourceTagLinkageCommand | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ validateResourceTagLinkageCommand }) => {
      this.validateResourceTagLinkageCommand = validateResourceTagLinkageCommand;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
