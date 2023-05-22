import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IResourceWaitingForAssociationEvent } from '../resource-waiting-for-association-event.model';
import { ResourceWaitingForAssociationEventService } from '../service/resource-waiting-for-association-event.service';

@Injectable({ providedIn: 'root' })
export class ResourceWaitingForAssociationEventRoutingResolveService implements Resolve<IResourceWaitingForAssociationEvent | null> {
  constructor(protected service: ResourceWaitingForAssociationEventService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IResourceWaitingForAssociationEvent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((resourceWaitingForAssociationEvent: HttpResponse<IResourceWaitingForAssociationEvent>) => {
          if (resourceWaitingForAssociationEvent.body) {
            return of(resourceWaitingForAssociationEvent.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
