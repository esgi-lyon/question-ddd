import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { TagChoicesListCommandFormService, TagChoicesListCommandFormGroup } from './tag-choices-list-command-form.service';
import { ITagChoicesListCommand } from '../tag-choices-list-command.model';
import { TagChoicesListCommandService } from '../service/tag-choices-list-command.service';

@Component({
  selector: 'jhi-tag-choices-list-command-update',
  templateUrl: './tag-choices-list-command-update.component.html',
})
export class TagChoicesListCommandUpdateComponent implements OnInit {
  isSaving = false;
  tagChoicesListCommand: ITagChoicesListCommand | null = null;

  editForm: TagChoicesListCommandFormGroup = this.tagChoicesListCommandFormService.createTagChoicesListCommandFormGroup();

  constructor(
    protected tagChoicesListCommandService: TagChoicesListCommandService,
    protected tagChoicesListCommandFormService: TagChoicesListCommandFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tagChoicesListCommand }) => {
      this.tagChoicesListCommand = tagChoicesListCommand;
      if (tagChoicesListCommand) {
        this.updateForm(tagChoicesListCommand);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tagChoicesListCommand = this.tagChoicesListCommandFormService.getTagChoicesListCommand(this.editForm);
    if (tagChoicesListCommand.id !== null) {
      this.subscribeToSaveResponse(this.tagChoicesListCommandService.update(tagChoicesListCommand));
    } else {
      this.subscribeToSaveResponse(this.tagChoicesListCommandService.create(tagChoicesListCommand));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITagChoicesListCommand>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(tagChoicesListCommand: ITagChoicesListCommand): void {
    this.tagChoicesListCommand = tagChoicesListCommand;
    this.tagChoicesListCommandFormService.resetForm(this.editForm, tagChoicesListCommand);
  }
}
