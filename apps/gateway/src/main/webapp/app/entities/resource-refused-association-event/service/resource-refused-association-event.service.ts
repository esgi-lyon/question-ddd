import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IResourceRefusedAssociationEvent, NewResourceRefusedAssociationEvent } from '../resource-refused-association-event.model';

export type PartialUpdateResourceRefusedAssociationEvent = Partial<IResourceRefusedAssociationEvent> &
  Pick<IResourceRefusedAssociationEvent, 'id'>;

export type EntityResponseType = HttpResponse<IResourceRefusedAssociationEvent>;
export type EntityArrayResponseType = HttpResponse<IResourceRefusedAssociationEvent[]>;

@Injectable({ providedIn: 'root' })
export class ResourceRefusedAssociationEventService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/resource-refused-association-events');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IResourceRefusedAssociationEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IResourceRefusedAssociationEvent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getResourceRefusedAssociationEventIdentifier(resourceRefusedAssociationEvent: Pick<IResourceRefusedAssociationEvent, 'id'>): number {
    return resourceRefusedAssociationEvent.id;
  }

  compareResourceRefusedAssociationEvent(
    o1: Pick<IResourceRefusedAssociationEvent, 'id'> | null,
    o2: Pick<IResourceRefusedAssociationEvent, 'id'> | null
  ): boolean {
    return o1 && o2
      ? this.getResourceRefusedAssociationEventIdentifier(o1) === this.getResourceRefusedAssociationEventIdentifier(o2)
      : o1 === o2;
  }

  addResourceRefusedAssociationEventToCollectionIfMissing<Type extends Pick<IResourceRefusedAssociationEvent, 'id'>>(
    resourceRefusedAssociationEventCollection: Type[],
    ...resourceRefusedAssociationEventsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const resourceRefusedAssociationEvents: Type[] = resourceRefusedAssociationEventsToCheck.filter(isPresent);
    if (resourceRefusedAssociationEvents.length > 0) {
      const resourceRefusedAssociationEventCollectionIdentifiers = resourceRefusedAssociationEventCollection.map(
        resourceRefusedAssociationEventItem => this.getResourceRefusedAssociationEventIdentifier(resourceRefusedAssociationEventItem)!
      );
      const resourceRefusedAssociationEventsToAdd = resourceRefusedAssociationEvents.filter(resourceRefusedAssociationEventItem => {
        const resourceRefusedAssociationEventIdentifier =
          this.getResourceRefusedAssociationEventIdentifier(resourceRefusedAssociationEventItem);
        if (resourceRefusedAssociationEventCollectionIdentifiers.includes(resourceRefusedAssociationEventIdentifier)) {
          return false;
        }
        resourceRefusedAssociationEventCollectionIdentifiers.push(resourceRefusedAssociationEventIdentifier);
        return true;
      });
      return [...resourceRefusedAssociationEventsToAdd, ...resourceRefusedAssociationEventCollection];
    }
    return resourceRefusedAssociationEventCollection;
  }
}
