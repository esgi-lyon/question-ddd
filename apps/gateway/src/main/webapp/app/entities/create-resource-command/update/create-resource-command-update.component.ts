import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { CreateResourceCommandFormService, CreateResourceCommandFormGroup } from './create-resource-command-form.service';
import { ICreateResourceCommand } from '../create-resource-command.model';
import { CreateResourceCommandService } from '../service/create-resource-command.service';
import { IQuestionResource } from 'app/entities/QuestionContext/question-resource/question-resource.model';
import { QuestionResourceService } from 'app/entities/QuestionContext/question-resource/service/question-resource.service';

@Component({
  selector: 'jhi-create-resource-command-update',
  templateUrl: './create-resource-command-update.component.html',
})
export class CreateResourceCommandUpdateComponent implements OnInit {
  isSaving = false;
  createResourceCommand: ICreateResourceCommand | null = null;

  questionResourcesSharedCollection: IQuestionResource[] = [];

  editForm: CreateResourceCommandFormGroup = this.createResourceCommandFormService.createCreateResourceCommandFormGroup();

  constructor(
    protected createResourceCommandService: CreateResourceCommandService,
    protected createResourceCommandFormService: CreateResourceCommandFormService,
    protected questionResourceService: QuestionResourceService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareQuestionResource = (o1: IQuestionResource | null, o2: IQuestionResource | null): boolean =>
    this.questionResourceService.compareQuestionResource(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ createResourceCommand }) => {
      this.createResourceCommand = createResourceCommand;
      if (createResourceCommand) {
        this.updateForm(createResourceCommand);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const createResourceCommand = this.createResourceCommandFormService.getCreateResourceCommand(this.editForm);
    if (createResourceCommand.id !== null) {
      this.subscribeToSaveResponse(this.createResourceCommandService.update(createResourceCommand));
    } else {
      this.subscribeToSaveResponse(this.createResourceCommandService.create(createResourceCommand));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICreateResourceCommand>>): void {
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

  protected updateForm(createResourceCommand: ICreateResourceCommand): void {
    this.createResourceCommand = createResourceCommand;
    this.createResourceCommandFormService.resetForm(this.editForm, createResourceCommand);

    this.questionResourcesSharedCollection = this.questionResourceService.addQuestionResourceToCollectionIfMissing<IQuestionResource>(
      this.questionResourcesSharedCollection,
      createResourceCommand.questionId
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
            this.createResourceCommand?.questionId
          )
        )
      )
      .subscribe((questionResources: IQuestionResource[]) => (this.questionResourcesSharedCollection = questionResources));
  }
}
