import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITagStatsViewedEvent, NewTagStatsViewedEvent } from '../tag-stats-viewed-event.model';

export type PartialUpdateTagStatsViewedEvent = Partial<ITagStatsViewedEvent> & Pick<ITagStatsViewedEvent, 'id'>;

export type EntityResponseType = HttpResponse<ITagStatsViewedEvent>;
export type EntityArrayResponseType = HttpResponse<ITagStatsViewedEvent[]>;

@Injectable({ providedIn: 'root' })
export class TagStatsViewedEventService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/tag-stats-viewed-events');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITagStatsViewedEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITagStatsViewedEvent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getTagStatsViewedEventIdentifier(tagStatsViewedEvent: Pick<ITagStatsViewedEvent, 'id'>): number {
    return tagStatsViewedEvent.id;
  }

  compareTagStatsViewedEvent(o1: Pick<ITagStatsViewedEvent, 'id'> | null, o2: Pick<ITagStatsViewedEvent, 'id'> | null): boolean {
    return o1 && o2 ? this.getTagStatsViewedEventIdentifier(o1) === this.getTagStatsViewedEventIdentifier(o2) : o1 === o2;
  }

  addTagStatsViewedEventToCollectionIfMissing<Type extends Pick<ITagStatsViewedEvent, 'id'>>(
    tagStatsViewedEventCollection: Type[],
    ...tagStatsViewedEventsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const tagStatsViewedEvents: Type[] = tagStatsViewedEventsToCheck.filter(isPresent);
    if (tagStatsViewedEvents.length > 0) {
      const tagStatsViewedEventCollectionIdentifiers = tagStatsViewedEventCollection.map(
        tagStatsViewedEventItem => this.getTagStatsViewedEventIdentifier(tagStatsViewedEventItem)!
      );
      const tagStatsViewedEventsToAdd = tagStatsViewedEvents.filter(tagStatsViewedEventItem => {
        const tagStatsViewedEventIdentifier = this.getTagStatsViewedEventIdentifier(tagStatsViewedEventItem);
        if (tagStatsViewedEventCollectionIdentifiers.includes(tagStatsViewedEventIdentifier)) {
          return false;
        }
        tagStatsViewedEventCollectionIdentifiers.push(tagStatsViewedEventIdentifier);
        return true;
      });
      return [...tagStatsViewedEventsToAdd, ...tagStatsViewedEventCollection];
    }
    return tagStatsViewedEventCollection;
  }
}
