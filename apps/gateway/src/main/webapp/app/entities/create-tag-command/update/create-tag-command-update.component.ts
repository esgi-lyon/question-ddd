import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { CreateTagCommandFormService, CreateTagCommandFormGroup } from './create-tag-command-form.service';
import { ICreateTagCommand } from '../create-tag-command.model';
import { CreateTagCommandService } from '../service/create-tag-command.service';
import { ITag } from 'app/entities/SkillContext/tag/tag.model';
import { TagService } from 'app/entities/SkillContext/tag/service/tag.service';

@Component({
  selector: 'jhi-create-tag-command-update',
  templateUrl: './create-tag-command-update.component.html',
})
export class CreateTagCommandUpdateComponent implements OnInit {
  isSaving = false;
  createTagCommand: ICreateTagCommand | null = null;

  tagsSharedCollection: ITag[] = [];

  editForm: CreateTagCommandFormGroup = this.createTagCommandFormService.createCreateTagCommandFormGroup();

  constructor(
    protected createTagCommandService: CreateTagCommandService,
    protected createTagCommandFormService: CreateTagCommandFormService,
    protected tagService: TagService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareTag = (o1: ITag | null, o2: ITag | null): boolean => this.tagService.compareTag(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ createTagCommand }) => {
      this.createTagCommand = createTagCommand;
      if (createTagCommand) {
        this.updateForm(createTagCommand);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const createTagCommand = this.createTagCommandFormService.getCreateTagCommand(this.editForm);
    if (createTagCommand.id !== null) {
      this.subscribeToSaveResponse(this.createTagCommandService.update(createTagCommand));
    } else {
      this.subscribeToSaveResponse(this.createTagCommandService.create(createTagCommand));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICreateTagCommand>>): void {
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

  protected updateForm(createTagCommand: ICreateTagCommand): void {
    this.createTagCommand = createTagCommand;
    this.createTagCommandFormService.resetForm(this.editForm, createTagCommand);

    this.tagsSharedCollection = this.tagService.addTagToCollectionIfMissing<ITag>(this.tagsSharedCollection, createTagCommand.tag);
  }

  protected loadRelationshipsOptions(): void {
    this.tagService
      .query()
      .pipe(map((res: HttpResponse<ITag[]>) => res.body ?? []))
      .pipe(map((tags: ITag[]) => this.tagService.addTagToCollectionIfMissing<ITag>(tags, this.createTagCommand?.tag)))
      .subscribe((tags: ITag[]) => (this.tagsSharedCollection = tags));
  }
}
