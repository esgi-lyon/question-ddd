import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { RejectResourceTagCommandFormService, RejectResourceTagCommandFormGroup } from './reject-resource-tag-command-form.service';
import { IRejectResourceTagCommand } from '../reject-resource-tag-command.model';
import { RejectResourceTagCommandService } from '../service/reject-resource-tag-command.service';
import { IQuestionResource } from 'app/entities/QuestionContext/question-resource/question-resource.model';
import { QuestionResourceService } from 'app/entities/QuestionContext/question-resource/service/question-resource.service';

@Component({
  selector: 'jhi-reject-resource-tag-command-update',
  templateUrl: './reject-resource-tag-command-update.component.html',
})
export class RejectResourceTagCommandUpdateComponent implements OnInit {
  isSaving = false;
  rejectResourceTagCommand: IRejectResourceTagCommand | null = null;

  questionResourcesSharedCollection: IQuestionResource[] = [];

  editForm: RejectResourceTagCommandFormGroup = this.rejectResourceTagCommandFormService.createRejectResourceTagCommandFormGroup();

  constructor(
    protected rejectResourceTagCommandService: RejectResourceTagCommandService,
    protected rejectResourceTagCommandFormService: RejectResourceTagCommandFormService,
    protected questionResourceService: QuestionResourceService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareQuestionResource = (o1: IQuestionResource | null, o2: IQuestionResource | null): boolean =>
    this.questionResourceService.compareQuestionResource(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rejectResourceTagCommand }) => {
      this.rejectResourceTagCommand = rejectResourceTagCommand;
      if (rejectResourceTagCommand) {
        this.updateForm(rejectResourceTagCommand);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const rejectResourceTagCommand = this.rejectResourceTagCommandFormService.getRejectResourceTagCommand(this.editForm);
    if (rejectResourceTagCommand.id !== null) {
      this.subscribeToSaveResponse(this.rejectResourceTagCommandService.update(rejectResourceTagCommand));
    } else {
      this.subscribeToSaveResponse(this.rejectResourceTagCommandService.create(rejectResourceTagCommand));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRejectResourceTagCommand>>): void {
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

  protected updateForm(rejectResourceTagCommand: IRejectResourceTagCommand): void {
    this.rejectResourceTagCommand = rejectResourceTagCommand;
    this.rejectResourceTagCommandFormService.resetForm(this.editForm, rejectResourceTagCommand);

    this.questionResourcesSharedCollection = this.questionResourceService.addQuestionResourceToCollectionIfMissing<IQuestionResource>(
      this.questionResourcesSharedCollection,
      rejectResourceTagCommand.questionId
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
            this.rejectResourceTagCommand?.questionId
          )
        )
      )
      .subscribe((questionResources: IQuestionResource[]) => (this.questionResourcesSharedCollection = questionResources));
  }
}
