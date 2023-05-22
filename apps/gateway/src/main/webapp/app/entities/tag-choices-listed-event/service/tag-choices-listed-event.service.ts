import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITagChoicesListedEvent, NewTagChoicesListedEvent } from '../tag-choices-listed-event.model';

export type PartialUpdateTagChoicesListedEvent = Partial<ITagChoicesListedEvent> & Pick<ITagChoicesListedEvent, 'id'>;

export type EntityResponseType = HttpResponse<ITagChoicesListedEvent>;
export type EntityArrayResponseType = HttpResponse<ITagChoicesListedEvent[]>;

@Injectable({ providedIn: 'root' })
export class TagChoicesListedEventService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/tag-choices-listed-events');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITagChoicesListedEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITagChoicesListedEvent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getTagChoicesListedEventIdentifier(tagChoicesListedEvent: Pick<ITagChoicesListedEvent, 'id'>): number {
    return tagChoicesListedEvent.id;
  }

  compareTagChoicesListedEvent(o1: Pick<ITagChoicesListedEvent, 'id'> | null, o2: Pick<ITagChoicesListedEvent, 'id'> | null): boolean {
    return o1 && o2 ? this.getTagChoicesListedEventIdentifier(o1) === this.getTagChoicesListedEventIdentifier(o2) : o1 === o2;
  }

  addTagChoicesListedEventToCollectionIfMissing<Type extends Pick<ITagChoicesListedEvent, 'id'>>(
    tagChoicesListedEventCollection: Type[],
    ...tagChoicesListedEventsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const tagChoicesListedEvents: Type[] = tagChoicesListedEventsToCheck.filter(isPresent);
    if (tagChoicesListedEvents.length > 0) {
      const tagChoicesListedEventCollectionIdentifiers = tagChoicesListedEventCollection.map(
        tagChoicesListedEventItem => this.getTagChoicesListedEventIdentifier(tagChoicesListedEventItem)!
      );
      const tagChoicesListedEventsToAdd = tagChoicesListedEvents.filter(tagChoicesListedEventItem => {
        const tagChoicesListedEventIdentifier = this.getTagChoicesListedEventIdentifier(tagChoicesListedEventItem);
        if (tagChoicesListedEventCollectionIdentifiers.includes(tagChoicesListedEventIdentifier)) {
          return false;
        }
        tagChoicesListedEventCollectionIdentifiers.push(tagChoicesListedEventIdentifier);
        return true;
      });
      return [...tagChoicesListedEventsToAdd, ...tagChoicesListedEventCollection];
    }
    return tagChoicesListedEventCollection;
  }
}
