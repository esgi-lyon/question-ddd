import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITagCreatedEvent, NewTagCreatedEvent } from '../tag-created-event.model';

export type PartialUpdateTagCreatedEvent = Partial<ITagCreatedEvent> & Pick<ITagCreatedEvent, 'id'>;

export type EntityResponseType = HttpResponse<ITagCreatedEvent>;
export type EntityArrayResponseType = HttpResponse<ITagCreatedEvent[]>;

@Injectable({ providedIn: 'root' })
export class TagCreatedEventService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/tag-created-events');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITagCreatedEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITagCreatedEvent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getTagCreatedEventIdentifier(tagCreatedEvent: Pick<ITagCreatedEvent, 'id'>): number {
    return tagCreatedEvent.id;
  }

  compareTagCreatedEvent(o1: Pick<ITagCreatedEvent, 'id'> | null, o2: Pick<ITagCreatedEvent, 'id'> | null): boolean {
    return o1 && o2 ? this.getTagCreatedEventIdentifier(o1) === this.getTagCreatedEventIdentifier(o2) : o1 === o2;
  }

  addTagCreatedEventToCollectionIfMissing<Type extends Pick<ITagCreatedEvent, 'id'>>(
    tagCreatedEventCollection: Type[],
    ...tagCreatedEventsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const tagCreatedEvents: Type[] = tagCreatedEventsToCheck.filter(isPresent);
    if (tagCreatedEvents.length > 0) {
      const tagCreatedEventCollectionIdentifiers = tagCreatedEventCollection.map(
        tagCreatedEventItem => this.getTagCreatedEventIdentifier(tagCreatedEventItem)!
      );
      const tagCreatedEventsToAdd = tagCreatedEvents.filter(tagCreatedEventItem => {
        const tagCreatedEventIdentifier = this.getTagCreatedEventIdentifier(tagCreatedEventItem);
        if (tagCreatedEventCollectionIdentifiers.includes(tagCreatedEventIdentifier)) {
          return false;
        }
        tagCreatedEventCollectionIdentifiers.push(tagCreatedEventIdentifier);
        return true;
      });
      return [...tagCreatedEventsToAdd, ...tagCreatedEventCollection];
    }
    return tagCreatedEventCollection;
  }
}
