import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Data, ParamMap, Router } from '@angular/router';
import { combineLatest, Observable, switchMap, tap } from 'rxjs';

import { IQuestionSentQuestionResourceTagId } from '../question-sent-question-resource-tag-id.model';
import { ASC, DESC, SORT, DEFAULT_SORT_DATA } from 'app/config/navigation.constants';
import {
  EntityArrayResponseType,
  QuestionSentQuestionResourceTagIdService,
} from '../service/question-sent-question-resource-tag-id.service';
import { SortService } from 'app/shared/sort/sort.service';

@Component({
  selector: 'jhi-question-sent-question-resource-tag-id',
  templateUrl: './question-sent-question-resource-tag-id.component.html',
})
export class QuestionSentQuestionResourceTagIdComponent implements OnInit {
  questionSentQuestionResourceTagIds?: IQuestionSentQuestionResourceTagId[];
  isLoading = false;

  predicate = 'id';
  ascending = true;

  constructor(
    protected questionSentQuestionResourceTagIdService: QuestionSentQuestionResourceTagIdService,
    protected activatedRoute: ActivatedRoute,
    public router: Router,
    protected sortService: SortService
  ) {}

  trackId = (_index: number, item: IQuestionSentQuestionResourceTagId): number =>
    this.questionSentQuestionResourceTagIdService.getQuestionSentQuestionResourceTagIdIdentifier(item);

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.loadFromBackendWithRouteInformations().subscribe({
      next: (res: EntityArrayResponseType) => {
        this.onResponseSuccess(res);
      },
    });
  }

  navigateToWithComponentValues(): void {
    this.handleNavigation(this.predicate, this.ascending);
  }

  protected loadFromBackendWithRouteInformations(): Observable<EntityArrayResponseType> {
    return combineLatest([this.activatedRoute.queryParamMap, this.activatedRoute.data]).pipe(
      tap(([params, data]) => this.fillComponentAttributeFromRoute(params, data)),
      switchMap(() => this.queryBackend(this.predicate, this.ascending))
    );
  }

  protected fillComponentAttributeFromRoute(params: ParamMap, data: Data): void {
    const sort = (params.get(SORT) ?? data[DEFAULT_SORT_DATA]).split(',');
    this.predicate = sort[0];
    this.ascending = sort[1] === ASC;
  }

  protected onResponseSuccess(response: EntityArrayResponseType): void {
    const dataFromBody = this.fillComponentAttributesFromResponseBody(response.body);
    this.questionSentQuestionResourceTagIds = this.refineData(dataFromBody);
  }

  protected refineData(data: IQuestionSentQuestionResourceTagId[]): IQuestionSentQuestionResourceTagId[] {
    return data.sort(this.sortService.startSort(this.predicate, this.ascending ? 1 : -1));
  }

  protected fillComponentAttributesFromResponseBody(
    data: IQuestionSentQuestionResourceTagId[] | null
  ): IQuestionSentQuestionResourceTagId[] {
    return data ?? [];
  }

  protected queryBackend(predicate?: string, ascending?: boolean): Observable<EntityArrayResponseType> {
    this.isLoading = true;
    const queryObject = {
      sort: this.getSortQueryParam(predicate, ascending),
    };
    return this.questionSentQuestionResourceTagIdService.query(queryObject).pipe(tap(() => (this.isLoading = false)));
  }

  protected handleNavigation(predicate?: string, ascending?: boolean): void {
    const queryParamsObj = {
      sort: this.getSortQueryParam(predicate, ascending),
    };

    this.router.navigate(['./'], {
      relativeTo: this.activatedRoute,
      queryParams: queryParamsObj,
    });
  }

  protected getSortQueryParam(predicate = this.predicate, ascending = this.ascending): string[] {
    const ascendingQueryParam = ascending ? ASC : DESC;
    if (predicate === '') {
      return [];
    } else {
      return [predicate + ',' + ascendingQueryParam];
    }
  }
}
