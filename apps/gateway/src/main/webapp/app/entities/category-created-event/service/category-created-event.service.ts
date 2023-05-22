import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICategoryCreatedEvent, NewCategoryCreatedEvent } from '../category-created-event.model';

export type PartialUpdateCategoryCreatedEvent = Partial<ICategoryCreatedEvent> & Pick<ICategoryCreatedEvent, 'id'>;

export type EntityResponseType = HttpResponse<ICategoryCreatedEvent>;
export type EntityArrayResponseType = HttpResponse<ICategoryCreatedEvent[]>;

@Injectable({ providedIn: 'root' })
export class CategoryCreatedEventService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/category-created-events');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICategoryCreatedEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICategoryCreatedEvent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getCategoryCreatedEventIdentifier(categoryCreatedEvent: Pick<ICategoryCreatedEvent, 'id'>): number {
    return categoryCreatedEvent.id;
  }

  compareCategoryCreatedEvent(o1: Pick<ICategoryCreatedEvent, 'id'> | null, o2: Pick<ICategoryCreatedEvent, 'id'> | null): boolean {
    return o1 && o2 ? this.getCategoryCreatedEventIdentifier(o1) === this.getCategoryCreatedEventIdentifier(o2) : o1 === o2;
  }

  addCategoryCreatedEventToCollectionIfMissing<Type extends Pick<ICategoryCreatedEvent, 'id'>>(
    categoryCreatedEventCollection: Type[],
    ...categoryCreatedEventsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const categoryCreatedEvents: Type[] = categoryCreatedEventsToCheck.filter(isPresent);
    if (categoryCreatedEvents.length > 0) {
      const categoryCreatedEventCollectionIdentifiers = categoryCreatedEventCollection.map(
        categoryCreatedEventItem => this.getCategoryCreatedEventIdentifier(categoryCreatedEventItem)!
      );
      const categoryCreatedEventsToAdd = categoryCreatedEvents.filter(categoryCreatedEventItem => {
        const categoryCreatedEventIdentifier = this.getCategoryCreatedEventIdentifier(categoryCreatedEventItem);
        if (categoryCreatedEventCollectionIdentifiers.includes(categoryCreatedEventIdentifier)) {
          return false;
        }
        categoryCreatedEventCollectionIdentifiers.push(categoryCreatedEventIdentifier);
        return true;
      });
      return [...categoryCreatedEventsToAdd, ...categoryCreatedEventCollection];
    }
    return categoryCreatedEventCollection;
  }
}
