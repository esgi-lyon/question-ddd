import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import {
  IResourceWaitingForAssociationEvent,
  NewResourceWaitingForAssociationEvent,
} from '../resource-waiting-for-association-event.model';

export type PartialUpdateResourceWaitingForAssociationEvent = Partial<IResourceWaitingForAssociationEvent> &
  Pick<IResourceWaitingForAssociationEvent, 'id'>;

export type EntityResponseType = HttpResponse<IResourceWaitingForAssociationEvent>;
export type EntityArrayResponseType = HttpResponse<IResourceWaitingForAssociationEvent[]>;

@Injectable({ providedIn: 'root' })
export class ResourceWaitingForAssociationEventService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/resource-waiting-for-association-events');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IResourceWaitingForAssociationEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IResourceWaitingForAssociationEvent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getResourceWaitingForAssociationEventIdentifier(
    resourceWaitingForAssociationEvent: Pick<IResourceWaitingForAssociationEvent, 'id'>
  ): number {
    return resourceWaitingForAssociationEvent.id;
  }

  compareResourceWaitingForAssociationEvent(
    o1: Pick<IResourceWaitingForAssociationEvent, 'id'> | null,
    o2: Pick<IResourceWaitingForAssociationEvent, 'id'> | null
  ): boolean {
    return o1 && o2
      ? this.getResourceWaitingForAssociationEventIdentifier(o1) === this.getResourceWaitingForAssociationEventIdentifier(o2)
      : o1 === o2;
  }

  addResourceWaitingForAssociationEventToCollectionIfMissing<Type extends Pick<IResourceWaitingForAssociationEvent, 'id'>>(
    resourceWaitingForAssociationEventCollection: Type[],
    ...resourceWaitingForAssociationEventsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const resourceWaitingForAssociationEvents: Type[] = resourceWaitingForAssociationEventsToCheck.filter(isPresent);
    if (resourceWaitingForAssociationEvents.length > 0) {
      const resourceWaitingForAssociationEventCollectionIdentifiers = resourceWaitingForAssociationEventCollection.map(
        resourceWaitingForAssociationEventItem =>
          this.getResourceWaitingForAssociationEventIdentifier(resourceWaitingForAssociationEventItem)!
      );
      const resourceWaitingForAssociationEventsToAdd = resourceWaitingForAssociationEvents.filter(
        resourceWaitingForAssociationEventItem => {
          const resourceWaitingForAssociationEventIdentifier = this.getResourceWaitingForAssociationEventIdentifier(
            resourceWaitingForAssociationEventItem
          );
          if (resourceWaitingForAssociationEventCollectionIdentifiers.includes(resourceWaitingForAssociationEventIdentifier)) {
            return false;
          }
          resourceWaitingForAssociationEventCollectionIdentifiers.push(resourceWaitingForAssociationEventIdentifier);
          return true;
        }
      );
      return [...resourceWaitingForAssociationEventsToAdd, ...resourceWaitingForAssociationEventCollection];
    }
    return resourceWaitingForAssociationEventCollection;
  }
}
