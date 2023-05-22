import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { QuestionResourceFormService, QuestionResourceFormGroup } from './question-resource-form.service';
import { IQuestionResource } from '../question-resource.model';
import { QuestionResourceService } from '../service/question-resource.service';
import { States } from 'app/entities/enumerations/states.model';
import { Types } from 'app/entities/enumerations/types.model';

@Component({
  selector: 'jhi-question-resource-update',
  templateUrl: './question-resource-update.component.html',
})
export class QuestionResourceUpdateComponent implements OnInit {
  isSaving = false;
  questionResource: IQuestionResource | null = null;
  statesValues = Object.keys(States);
  typesValues = Object.keys(Types);

  editForm: QuestionResourceFormGroup = this.questionResourceFormService.createQuestionResourceFormGroup();

  constructor(
    protected questionResourceService: QuestionResourceService,
    protected questionResourceFormService: QuestionResourceFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ questionResource }) => {
      this.questionResource = questionResource;
      if (questionResource) {
        this.updateForm(questionResource);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const questionResource = this.questionResourceFormService.getQuestionResource(this.editForm);
    if (questionResource.id !== null) {
      this.subscribeToSaveResponse(this.questionResourceService.update(questionResource));
    } else {
      this.subscribeToSaveResponse(this.questionResourceService.create(questionResource));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuestionResource>>): void {
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

  protected updateForm(questionResource: IQuestionResource): void {
    this.questionResource = questionResource;
    this.questionResourceFormService.resetForm(this.editForm, questionResource);
  }
}
