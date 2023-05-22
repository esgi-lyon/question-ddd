import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEvaluationCreatedEvent, NewEvaluationCreatedEvent } from '../evaluation-created-event.model';

export type PartialUpdateEvaluationCreatedEvent = Partial<IEvaluationCreatedEvent> & Pick<IEvaluationCreatedEvent, 'id'>;

export type EntityResponseType = HttpResponse<IEvaluationCreatedEvent>;
export type EntityArrayResponseType = HttpResponse<IEvaluationCreatedEvent[]>;

@Injectable({ providedIn: 'root' })
export class EvaluationCreatedEventService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/evaluation-created-events');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEvaluationCreatedEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEvaluationCreatedEvent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getEvaluationCreatedEventIdentifier(evaluationCreatedEvent: Pick<IEvaluationCreatedEvent, 'id'>): number {
    return evaluationCreatedEvent.id;
  }

  compareEvaluationCreatedEvent(o1: Pick<IEvaluationCreatedEvent, 'id'> | null, o2: Pick<IEvaluationCreatedEvent, 'id'> | null): boolean {
    return o1 && o2 ? this.getEvaluationCreatedEventIdentifier(o1) === this.getEvaluationCreatedEventIdentifier(o2) : o1 === o2;
  }

  addEvaluationCreatedEventToCollectionIfMissing<Type extends Pick<IEvaluationCreatedEvent, 'id'>>(
    evaluationCreatedEventCollection: Type[],
    ...evaluationCreatedEventsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const evaluationCreatedEvents: Type[] = evaluationCreatedEventsToCheck.filter(isPresent);
    if (evaluationCreatedEvents.length > 0) {
      const evaluationCreatedEventCollectionIdentifiers = evaluationCreatedEventCollection.map(
        evaluationCreatedEventItem => this.getEvaluationCreatedEventIdentifier(evaluationCreatedEventItem)!
      );
      const evaluationCreatedEventsToAdd = evaluationCreatedEvents.filter(evaluationCreatedEventItem => {
        const evaluationCreatedEventIdentifier = this.getEvaluationCreatedEventIdentifier(evaluationCreatedEventItem);
        if (evaluationCreatedEventCollectionIdentifiers.includes(evaluationCreatedEventIdentifier)) {
          return false;
        }
        evaluationCreatedEventCollectionIdentifiers.push(evaluationCreatedEventIdentifier);
        return true;
      });
      return [...evaluationCreatedEventsToAdd, ...evaluationCreatedEventCollection];
    }
    return evaluationCreatedEventCollection;
  }
}
