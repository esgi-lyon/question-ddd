import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAnswerCheckedEvent, NewAnswerCheckedEvent } from '../answer-checked-event.model';

export type PartialUpdateAnswerCheckedEvent = Partial<IAnswerCheckedEvent> & Pick<IAnswerCheckedEvent, 'id'>;

export type EntityResponseType = HttpResponse<IAnswerCheckedEvent>;
export type EntityArrayResponseType = HttpResponse<IAnswerCheckedEvent[]>;

@Injectable({ providedIn: 'root' })
export class AnswerCheckedEventService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/answer-checked-events');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAnswerCheckedEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAnswerCheckedEvent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getAnswerCheckedEventIdentifier(answerCheckedEvent: Pick<IAnswerCheckedEvent, 'id'>): number {
    return answerCheckedEvent.id;
  }

  compareAnswerCheckedEvent(o1: Pick<IAnswerCheckedEvent, 'id'> | null, o2: Pick<IAnswerCheckedEvent, 'id'> | null): boolean {
    return o1 && o2 ? this.getAnswerCheckedEventIdentifier(o1) === this.getAnswerCheckedEventIdentifier(o2) : o1 === o2;
  }

  addAnswerCheckedEventToCollectionIfMissing<Type extends Pick<IAnswerCheckedEvent, 'id'>>(
    answerCheckedEventCollection: Type[],
    ...answerCheckedEventsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const answerCheckedEvents: Type[] = answerCheckedEventsToCheck.filter(isPresent);
    if (answerCheckedEvents.length > 0) {
      const answerCheckedEventCollectionIdentifiers = answerCheckedEventCollection.map(
        answerCheckedEventItem => this.getAnswerCheckedEventIdentifier(answerCheckedEventItem)!
      );
      const answerCheckedEventsToAdd = answerCheckedEvents.filter(answerCheckedEventItem => {
        const answerCheckedEventIdentifier = this.getAnswerCheckedEventIdentifier(answerCheckedEventItem);
        if (answerCheckedEventCollectionIdentifiers.includes(answerCheckedEventIdentifier)) {
          return false;
        }
        answerCheckedEventCollectionIdentifiers.push(answerCheckedEventIdentifier);
        return true;
      });
      return [...answerCheckedEventsToAdd, ...answerCheckedEventCollection];
    }
    return answerCheckedEventCollection;
  }
}
