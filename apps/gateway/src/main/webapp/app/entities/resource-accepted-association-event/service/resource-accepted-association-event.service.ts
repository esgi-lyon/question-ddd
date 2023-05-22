import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IResourceAcceptedAssociationEvent, NewResourceAcceptedAssociationEvent } from '../resource-accepted-association-event.model';

export type PartialUpdateResourceAcceptedAssociationEvent = Partial<IResourceAcceptedAssociationEvent> &
  Pick<IResourceAcceptedAssociationEvent, 'id'>;

export type EntityResponseType = HttpResponse<IResourceAcceptedAssociationEvent>;
export type EntityArrayResponseType = HttpResponse<IResourceAcceptedAssociationEvent[]>;

@Injectable({ providedIn: 'root' })
export class ResourceAcceptedAssociationEventService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/resource-accepted-association-events');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IResourceAcceptedAssociationEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IResourceAcceptedAssociationEvent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getResourceAcceptedAssociationEventIdentifier(resourceAcceptedAssociationEvent: Pick<IResourceAcceptedAssociationEvent, 'id'>): number {
    return resourceAcceptedAssociationEvent.id;
  }

  compareResourceAcceptedAssociationEvent(
    o1: Pick<IResourceAcceptedAssociationEvent, 'id'> | null,
    o2: Pick<IResourceAcceptedAssociationEvent, 'id'> | null
  ): boolean {
    return o1 && o2
      ? this.getResourceAcceptedAssociationEventIdentifier(o1) === this.getResourceAcceptedAssociationEventIdentifier(o2)
      : o1 === o2;
  }

  addResourceAcceptedAssociationEventToCollectionIfMissing<Type extends Pick<IResourceAcceptedAssociationEvent, 'id'>>(
    resourceAcceptedAssociationEventCollection: Type[],
    ...resourceAcceptedAssociationEventsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const resourceAcceptedAssociationEvents: Type[] = resourceAcceptedAssociationEventsToCheck.filter(isPresent);
    if (resourceAcceptedAssociationEvents.length > 0) {
      const resourceAcceptedAssociationEventCollectionIdentifiers = resourceAcceptedAssociationEventCollection.map(
        resourceAcceptedAssociationEventItem => this.getResourceAcceptedAssociationEventIdentifier(resourceAcceptedAssociationEventItem)!
      );
      const resourceAcceptedAssociationEventsToAdd = resourceAcceptedAssociationEvents.filter(resourceAcceptedAssociationEventItem => {
        const resourceAcceptedAssociationEventIdentifier = this.getResourceAcceptedAssociationEventIdentifier(
          resourceAcceptedAssociationEventItem
        );
        if (resourceAcceptedAssociationEventCollectionIdentifiers.includes(resourceAcceptedAssociationEventIdentifier)) {
          return false;
        }
        resourceAcceptedAssociationEventCollectionIdentifiers.push(resourceAcceptedAssociationEventIdentifier);
        return true;
      });
      return [...resourceAcceptedAssociationEventsToAdd, ...resourceAcceptedAssociationEventCollection];
    }
    return resourceAcceptedAssociationEventCollection;
  }
}
