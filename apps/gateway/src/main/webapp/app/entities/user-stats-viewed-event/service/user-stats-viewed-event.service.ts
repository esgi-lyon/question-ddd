import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IUserStatsViewedEvent, NewUserStatsViewedEvent } from '../user-stats-viewed-event.model';

export type PartialUpdateUserStatsViewedEvent = Partial<IUserStatsViewedEvent> & Pick<IUserStatsViewedEvent, 'id'>;

export type EntityResponseType = HttpResponse<IUserStatsViewedEvent>;
export type EntityArrayResponseType = HttpResponse<IUserStatsViewedEvent[]>;

@Injectable({ providedIn: 'root' })
export class UserStatsViewedEventService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/user-stats-viewed-events');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUserStatsViewedEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUserStatsViewedEvent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getUserStatsViewedEventIdentifier(userStatsViewedEvent: Pick<IUserStatsViewedEvent, 'id'>): number {
    return userStatsViewedEvent.id;
  }

  compareUserStatsViewedEvent(o1: Pick<IUserStatsViewedEvent, 'id'> | null, o2: Pick<IUserStatsViewedEvent, 'id'> | null): boolean {
    return o1 && o2 ? this.getUserStatsViewedEventIdentifier(o1) === this.getUserStatsViewedEventIdentifier(o2) : o1 === o2;
  }

  addUserStatsViewedEventToCollectionIfMissing<Type extends Pick<IUserStatsViewedEvent, 'id'>>(
    userStatsViewedEventCollection: Type[],
    ...userStatsViewedEventsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const userStatsViewedEvents: Type[] = userStatsViewedEventsToCheck.filter(isPresent);
    if (userStatsViewedEvents.length > 0) {
      const userStatsViewedEventCollectionIdentifiers = userStatsViewedEventCollection.map(
        userStatsViewedEventItem => this.getUserStatsViewedEventIdentifier(userStatsViewedEventItem)!
      );
      const userStatsViewedEventsToAdd = userStatsViewedEvents.filter(userStatsViewedEventItem => {
        const userStatsViewedEventIdentifier = this.getUserStatsViewedEventIdentifier(userStatsViewedEventItem);
        if (userStatsViewedEventCollectionIdentifiers.includes(userStatsViewedEventIdentifier)) {
          return false;
        }
        userStatsViewedEventCollectionIdentifiers.push(userStatsViewedEventIdentifier);
        return true;
      });
      return [...userStatsViewedEventsToAdd, ...userStatsViewedEventCollection];
    }
    return userStatsViewedEventCollection;
  }
}
