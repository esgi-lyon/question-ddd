import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { INotifiedQuestionEvent, NewNotifiedQuestionEvent } from '../notified-question-event.model';

export type PartialUpdateNotifiedQuestionEvent = Partial<INotifiedQuestionEvent> & Pick<INotifiedQuestionEvent, 'id'>;

export type EntityResponseType = HttpResponse<INotifiedQuestionEvent>;
export type EntityArrayResponseType = HttpResponse<INotifiedQuestionEvent[]>;

@Injectable({ providedIn: 'root' })
export class NotifiedQuestionEventService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/notified-question-events');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INotifiedQuestionEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INotifiedQuestionEvent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getNotifiedQuestionEventIdentifier(notifiedQuestionEvent: Pick<INotifiedQuestionEvent, 'id'>): number {
    return notifiedQuestionEvent.id;
  }

  compareNotifiedQuestionEvent(o1: Pick<INotifiedQuestionEvent, 'id'> | null, o2: Pick<INotifiedQuestionEvent, 'id'> | null): boolean {
    return o1 && o2 ? this.getNotifiedQuestionEventIdentifier(o1) === this.getNotifiedQuestionEventIdentifier(o2) : o1 === o2;
  }

  addNotifiedQuestionEventToCollectionIfMissing<Type extends Pick<INotifiedQuestionEvent, 'id'>>(
    notifiedQuestionEventCollection: Type[],
    ...notifiedQuestionEventsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const notifiedQuestionEvents: Type[] = notifiedQuestionEventsToCheck.filter(isPresent);
    if (notifiedQuestionEvents.length > 0) {
      const notifiedQuestionEventCollectionIdentifiers = notifiedQuestionEventCollection.map(
        notifiedQuestionEventItem => this.getNotifiedQuestionEventIdentifier(notifiedQuestionEventItem)!
      );
      const notifiedQuestionEventsToAdd = notifiedQuestionEvents.filter(notifiedQuestionEventItem => {
        const notifiedQuestionEventIdentifier = this.getNotifiedQuestionEventIdentifier(notifiedQuestionEventItem);
        if (notifiedQuestionEventCollectionIdentifiers.includes(notifiedQuestionEventIdentifier)) {
          return false;
        }
        notifiedQuestionEventCollectionIdentifiers.push(notifiedQuestionEventIdentifier);
        return true;
      });
      return [...notifiedQuestionEventsToAdd, ...notifiedQuestionEventCollection];
    }
    return notifiedQuestionEventCollection;
  }
}
