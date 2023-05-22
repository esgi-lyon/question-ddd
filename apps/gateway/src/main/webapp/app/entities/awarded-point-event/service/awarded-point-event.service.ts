import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAwardedPointEvent, NewAwardedPointEvent } from '../awarded-point-event.model';

export type PartialUpdateAwardedPointEvent = Partial<IAwardedPointEvent> & Pick<IAwardedPointEvent, 'id'>;

export type EntityResponseType = HttpResponse<IAwardedPointEvent>;
export type EntityArrayResponseType = HttpResponse<IAwardedPointEvent[]>;

@Injectable({ providedIn: 'root' })
export class AwardedPointEventService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/awarded-point-events');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAwardedPointEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAwardedPointEvent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getAwardedPointEventIdentifier(awardedPointEvent: Pick<IAwardedPointEvent, 'id'>): number {
    return awardedPointEvent.id;
  }

  compareAwardedPointEvent(o1: Pick<IAwardedPointEvent, 'id'> | null, o2: Pick<IAwardedPointEvent, 'id'> | null): boolean {
    return o1 && o2 ? this.getAwardedPointEventIdentifier(o1) === this.getAwardedPointEventIdentifier(o2) : o1 === o2;
  }

  addAwardedPointEventToCollectionIfMissing<Type extends Pick<IAwardedPointEvent, 'id'>>(
    awardedPointEventCollection: Type[],
    ...awardedPointEventsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const awardedPointEvents: Type[] = awardedPointEventsToCheck.filter(isPresent);
    if (awardedPointEvents.length > 0) {
      const awardedPointEventCollectionIdentifiers = awardedPointEventCollection.map(
        awardedPointEventItem => this.getAwardedPointEventIdentifier(awardedPointEventItem)!
      );
      const awardedPointEventsToAdd = awardedPointEvents.filter(awardedPointEventItem => {
        const awardedPointEventIdentifier = this.getAwardedPointEventIdentifier(awardedPointEventItem);
        if (awardedPointEventCollectionIdentifiers.includes(awardedPointEventIdentifier)) {
          return false;
        }
        awardedPointEventCollectionIdentifiers.push(awardedPointEventIdentifier);
        return true;
      });
      return [...awardedPointEventsToAdd, ...awardedPointEventCollection];
    }
    return awardedPointEventCollection;
  }
}
