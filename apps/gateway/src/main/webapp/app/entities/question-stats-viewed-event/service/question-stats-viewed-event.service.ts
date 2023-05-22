import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IQuestionStatsViewedEvent, NewQuestionStatsViewedEvent } from '../question-stats-viewed-event.model';

export type PartialUpdateQuestionStatsViewedEvent = Partial<IQuestionStatsViewedEvent> & Pick<IQuestionStatsViewedEvent, 'id'>;

export type EntityResponseType = HttpResponse<IQuestionStatsViewedEvent>;
export type EntityArrayResponseType = HttpResponse<IQuestionStatsViewedEvent[]>;

@Injectable({ providedIn: 'root' })
export class QuestionStatsViewedEventService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/question-stats-viewed-events');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IQuestionStatsViewedEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IQuestionStatsViewedEvent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getQuestionStatsViewedEventIdentifier(questionStatsViewedEvent: Pick<IQuestionStatsViewedEvent, 'id'>): number {
    return questionStatsViewedEvent.id;
  }

  compareQuestionStatsViewedEvent(
    o1: Pick<IQuestionStatsViewedEvent, 'id'> | null,
    o2: Pick<IQuestionStatsViewedEvent, 'id'> | null
  ): boolean {
    return o1 && o2 ? this.getQuestionStatsViewedEventIdentifier(o1) === this.getQuestionStatsViewedEventIdentifier(o2) : o1 === o2;
  }

  addQuestionStatsViewedEventToCollectionIfMissing<Type extends Pick<IQuestionStatsViewedEvent, 'id'>>(
    questionStatsViewedEventCollection: Type[],
    ...questionStatsViewedEventsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const questionStatsViewedEvents: Type[] = questionStatsViewedEventsToCheck.filter(isPresent);
    if (questionStatsViewedEvents.length > 0) {
      const questionStatsViewedEventCollectionIdentifiers = questionStatsViewedEventCollection.map(
        questionStatsViewedEventItem => this.getQuestionStatsViewedEventIdentifier(questionStatsViewedEventItem)!
      );
      const questionStatsViewedEventsToAdd = questionStatsViewedEvents.filter(questionStatsViewedEventItem => {
        const questionStatsViewedEventIdentifier = this.getQuestionStatsViewedEventIdentifier(questionStatsViewedEventItem);
        if (questionStatsViewedEventCollectionIdentifiers.includes(questionStatsViewedEventIdentifier)) {
          return false;
        }
        questionStatsViewedEventCollectionIdentifiers.push(questionStatsViewedEventIdentifier);
        return true;
      });
      return [...questionStatsViewedEventsToAdd, ...questionStatsViewedEventCollection];
    }
    return questionStatsViewedEventCollection;
  }
}
