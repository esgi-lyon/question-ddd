import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import {
  ValidateResourceTagLinkageCommandFormService,
  ValidateResourceTagLinkageCommandFormGroup,
} from './validate-resource-tag-linkage-command-form.service';
import { IValidateResourceTagLinkageCommand } from '../validate-resource-tag-linkage-command.model';
import { ValidateResourceTagLinkageCommandService } from '../service/validate-resource-tag-linkage-command.service';
import { IQuestionResource } from 'app/entities/QuestionContext/question-resource/question-resource.model';
import { QuestionResourceService } from 'app/entities/QuestionContext/question-resource/service/question-resource.service';

@Component({
  selector: 'jhi-validate-resource-tag-linkage-command-update',
  templateUrl: './validate-resource-tag-linkage-command-update.component.html',
})
export class ValidateResourceTagLinkageCommandUpdateComponent implements OnInit {
  isSaving = false;
  validateResourceTagLinkageCommand: IValidateResourceTagLinkageCommand | null = null;

  questionResourcesSharedCollection: IQuestionResource[] = [];

  editForm: ValidateResourceTagLinkageCommandFormGroup =
    this.validateResourceTagLinkageCommandFormService.createValidateResourceTagLinkageCommandFormGroup();

  constructor(
    protected validateResourceTagLinkageCommandService: ValidateResourceTagLinkageCommandService,
    protected validateResourceTagLinkageCommandFormService: ValidateResourceTagLinkageCommandFormService,
    protected questionResourceService: QuestionResourceService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareQuestionResource = (o1: IQuestionResource | null, o2: IQuestionResource | null): boolean =>
    this.questionResourceService.compareQuestionResource(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ validateResourceTagLinkageCommand }) => {
      this.validateResourceTagLinkageCommand = validateResourceTagLinkageCommand;
      if (validateResourceTagLinkageCommand) {
        this.updateForm(validateResourceTagLinkageCommand);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const validateResourceTagLinkageCommand = this.validateResourceTagLinkageCommandFormService.getValidateResourceTagLinkageCommand(
      this.editForm
    );
    if (validateResourceTagLinkageCommand.id !== null) {
      this.subscribeToSaveResponse(this.validateResourceTagLinkageCommandService.update(validateResourceTagLinkageCommand));
    } else {
      this.subscribeToSaveResponse(this.validateResourceTagLinkageCommandService.create(validateResourceTagLinkageCommand));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IValidateResourceTagLinkageCommand>>): void {
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

  protected updateForm(validateResourceTagLinkageCommand: IValidateResourceTagLinkageCommand): void {
    this.validateResourceTagLinkageCommand = validateResourceTagLinkageCommand;
    this.validateResourceTagLinkageCommandFormService.resetForm(this.editForm, validateResourceTagLinkageCommand);

    this.questionResourcesSharedCollection = this.questionResourceService.addQuestionResourceToCollectionIfMissing<IQuestionResource>(
      this.questionResourcesSharedCollection,
      validateResourceTagLinkageCommand.questionId
    );
  }

  protected loadRelationshipsOptions(): void {
    this.questionResourceService
      .query()
      .pipe(map((res: HttpResponse<IQuestionResource[]>) => res.body ?? []))
      .pipe(
        map((questionResources: IQuestionResource[]) =>
          this.questionResourceService.addQuestionResourceToCollectionIfMissing<IQuestionResource>(
            questionResources,
            this.validateResourceTagLinkageCommand?.questionId
          )
        )
      )
      .subscribe((questionResources: IQuestionResource[]) => (this.questionResourcesSharedCollection = questionResources));
  }
}
