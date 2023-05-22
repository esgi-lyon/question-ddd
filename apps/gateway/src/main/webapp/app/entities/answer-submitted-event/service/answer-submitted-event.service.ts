import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAnswerSubmittedEvent, NewAnswerSubmittedEvent } from '../answer-submitted-event.model';

export type PartialUpdateAnswerSubmittedEvent = Partial<IAnswerSubmittedEvent> & Pick<IAnswerSubmittedEvent, 'id'>;

export type EntityResponseType = HttpResponse<IAnswerSubmittedEvent>;
export type EntityArrayResponseType = HttpResponse<IAnswerSubmittedEvent[]>;

@Injectable({ providedIn: 'root' })
export class AnswerSubmittedEventService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/answer-submitted-events');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAnswerSubmittedEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAnswerSubmittedEvent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getAnswerSubmittedEventIdentifier(answerSubmittedEvent: Pick<IAnswerSubmittedEvent, 'id'>): number {
    return answerSubmittedEvent.id;
  }

  compareAnswerSubmittedEvent(o1: Pick<IAnswerSubmittedEvent, 'id'> | null, o2: Pick<IAnswerSubmittedEvent, 'id'> | null): boolean {
    return o1 && o2 ? this.getAnswerSubmittedEventIdentifier(o1) === this.getAnswerSubmittedEventIdentifier(o2) : o1 === o2;
  }

  addAnswerSubmittedEventToCollectionIfMissing<Type extends Pick<IAnswerSubmittedEvent, 'id'>>(
    answerSubmittedEventCollection: Type[],
    ...answerSubmittedEventsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const answerSubmittedEvents: Type[] = answerSubmittedEventsToCheck.filter(isPresent);
    if (answerSubmittedEvents.length > 0) {
      const answerSubmittedEventCollectionIdentifiers = answerSubmittedEventCollection.map(
        answerSubmittedEventItem => this.getAnswerSubmittedEventIdentifier(answerSubmittedEventItem)!
      );
      const answerSubmittedEventsToAdd = answerSubmittedEvents.filter(answerSubmittedEventItem => {
        const answerSubmittedEventIdentifier = this.getAnswerSubmittedEventIdentifier(answerSubmittedEventItem);
        if (answerSubmittedEventCollectionIdentifiers.includes(answerSubmittedEventIdentifier)) {
          return false;
        }
        answerSubmittedEventCollectionIdentifiers.push(answerSubmittedEventIdentifier);
        return true;
      });
      return [...answerSubmittedEventsToAdd, ...answerSubmittedEventCollection];
    }
    return answerSubmittedEventCollection;
  }
}
