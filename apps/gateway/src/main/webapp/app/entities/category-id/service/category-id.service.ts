import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICategoryId, NewCategoryId } from '../category-id.model';

export type PartialUpdateCategoryId = Partial<ICategoryId> & Pick<ICategoryId, 'id'>;

export type EntityResponseType = HttpResponse<ICategoryId>;
export type EntityArrayResponseType = HttpResponse<ICategoryId[]>;

@Injectable({ providedIn: 'root' })
export class CategoryIdService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/category-ids');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICategoryId>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICategoryId[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getCategoryIdIdentifier(categoryId: Pick<ICategoryId, 'id'>): number {
    return categoryId.id;
  }

  compareCategoryId(o1: Pick<ICategoryId, 'id'> | null, o2: Pick<ICategoryId, 'id'> | null): boolean {
    return o1 && o2 ? this.getCategoryIdIdentifier(o1) === this.getCategoryIdIdentifier(o2) : o1 === o2;
  }

  addCategoryIdToCollectionIfMissing<Type extends Pick<ICategoryId, 'id'>>(
    categoryIdCollection: Type[],
    ...categoryIdsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const categoryIds: Type[] = categoryIdsToCheck.filter(isPresent);
    if (categoryIds.length > 0) {
      const categoryIdCollectionIdentifiers = categoryIdCollection.map(categoryIdItem => this.getCategoryIdIdentifier(categoryIdItem)!);
      const categoryIdsToAdd = categoryIds.filter(categoryIdItem => {
        const categoryIdIdentifier = this.getCategoryIdIdentifier(categoryIdItem);
        if (categoryIdCollectionIdentifiers.includes(categoryIdIdentifier)) {
          return false;
        }
        categoryIdCollectionIdentifiers.push(categoryIdIdentifier);
        return true;
      });
      return [...categoryIdsToAdd, ...categoryIdCollection];
    }
    return categoryIdCollection;
  }
}
