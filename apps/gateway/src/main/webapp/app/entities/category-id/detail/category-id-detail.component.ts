import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICategoryId } from '../category-id.model';

@Component({
  selector: 'jhi-category-id-detail',
  templateUrl: './category-id-detail.component.html',
})
export class CategoryIdDetailComponent implements OnInit {
  categoryId: ICategoryId | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ categoryId }) => {
      this.categoryId = categoryId;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
