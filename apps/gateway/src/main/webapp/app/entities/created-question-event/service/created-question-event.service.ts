import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICreatedQuestionEvent, NewCreatedQuestionEvent } from '../created-question-event.model';

export type PartialUpdateCreatedQuestionEvent = Partial<ICreatedQuestionEvent> & Pick<ICreatedQuestionEvent, 'id'>;

export type EntityResponseType = HttpResponse<ICreatedQuestionEvent>;
export type EntityArrayResponseType = HttpResponse<ICreatedQuestionEvent[]>;

@Injectable({ providedIn: 'root' })
export class CreatedQuestionEventService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/created-question-events');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICreatedQuestionEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICreatedQuestionEvent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getCreatedQuestionEventIdentifier(createdQuestionEvent: Pick<ICreatedQuestionEvent, 'id'>): number {
    return createdQuestionEvent.id;
  }

  compareCreatedQuestionEvent(o1: Pick<ICreatedQuestionEvent, 'id'> | null, o2: Pick<ICreatedQuestionEvent, 'id'> | null): boolean {
    return o1 && o2 ? this.getCreatedQuestionEventIdentifier(o1) === this.getCreatedQuestionEventIdentifier(o2) : o1 === o2;
  }

  addCreatedQuestionEventToCollectionIfMissing<Type extends Pick<ICreatedQuestionEvent, 'id'>>(
    createdQuestionEventCollection: Type[],
    ...createdQuestionEventsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const createdQuestionEvents: Type[] = createdQuestionEventsToCheck.filter(isPresent);
    if (createdQuestionEvents.length > 0) {
      const createdQuestionEventCollectionIdentifiers = createdQuestionEventCollection.map(
        createdQuestionEventItem => this.getCreatedQuestionEventIdentifier(createdQuestionEventItem)!
      );
      const createdQuestionEventsToAdd = createdQuestionEvents.filter(createdQuestionEventItem => {
        const createdQuestionEventIdentifier = this.getCreatedQuestionEventIdentifier(createdQuestionEventItem);
        if (createdQuestionEventCollectionIdentifiers.includes(createdQuestionEventIdentifier)) {
          return false;
        }
        createdQuestionEventCollectionIdentifiers.push(createdQuestionEventIdentifier);
        return true;
      });
      return [...createdQuestionEventsToAdd, ...createdQuestionEventCollection];
    }
    return createdQuestionEventCollection;
  }
}
