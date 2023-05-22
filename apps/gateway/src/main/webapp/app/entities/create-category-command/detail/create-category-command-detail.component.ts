import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICreateCategoryCommand } from '../create-category-command.model';

@Component({
  selector: 'jhi-create-category-command-detail',
  templateUrl: './create-category-command-detail.component.html',
})
export class CreateCategoryCommandDetailComponent implements OnInit {
  createCategoryCommand: ICreateCategoryCommand | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ createCategoryCommand }) => {
      this.createCategoryCommand = createCategoryCommand;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
