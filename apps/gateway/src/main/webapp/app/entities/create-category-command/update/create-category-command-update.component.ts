import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { CreateCategoryCommandFormService, CreateCategoryCommandFormGroup } from './create-category-command-form.service';
import { ICreateCategoryCommand } from '../create-category-command.model';
import { CreateCategoryCommandService } from '../service/create-category-command.service';
import { ICategory } from 'app/entities/SkillContext/category/category.model';
import { CategoryService } from 'app/entities/SkillContext/category/service/category.service';

@Component({
  selector: 'jhi-create-category-command-update',
  templateUrl: './create-category-command-update.component.html',
})
export class CreateCategoryCommandUpdateComponent implements OnInit {
  isSaving = false;
  createCategoryCommand: ICreateCategoryCommand | null = null;

  categoriesSharedCollection: ICategory[] = [];

  editForm: CreateCategoryCommandFormGroup = this.createCategoryCommandFormService.createCreateCategoryCommandFormGroup();

  constructor(
    protected createCategoryCommandService: CreateCategoryCommandService,
    protected createCategoryCommandFormService: CreateCategoryCommandFormService,
    protected categoryService: CategoryService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareCategory = (o1: ICategory | null, o2: ICategory | null): boolean => this.categoryService.compareCategory(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ createCategoryCommand }) => {
      this.createCategoryCommand = createCategoryCommand;
      if (createCategoryCommand) {
        this.updateForm(createCategoryCommand);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const createCategoryCommand = this.createCategoryCommandFormService.getCreateCategoryCommand(this.editForm);
    if (createCategoryCommand.id !== null) {
      this.subscribeToSaveResponse(this.createCategoryCommandService.update(createCategoryCommand));
    } else {
      this.subscribeToSaveResponse(this.createCategoryCommandService.create(createCategoryCommand));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICreateCategoryCommand>>): void {
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

  protected updateForm(createCategoryCommand: ICreateCategoryCommand): void {
    this.createCategoryCommand = createCategoryCommand;
    this.createCategoryCommandFormService.resetForm(this.editForm, createCategoryCommand);

    this.categoriesSharedCollection = this.categoryService.addCategoryToCollectionIfMissing<ICategory>(
      this.categoriesSharedCollection,
      createCategoryCommand.category
    );
  }

  protected loadRelationshipsOptions(): void {
    this.categoryService
      .query()
      .pipe(map((res: HttpResponse<ICategory[]>) => res.body ?? []))
      .pipe(
        map((categories: ICategory[]) =>
          this.categoryService.addCategoryToCollectionIfMissing<ICategory>(categories, this.createCategoryCommand?.category)
        )
      )
      .subscribe((categories: ICategory[]) => (this.categoriesSharedCollection = categories));
  }
}
