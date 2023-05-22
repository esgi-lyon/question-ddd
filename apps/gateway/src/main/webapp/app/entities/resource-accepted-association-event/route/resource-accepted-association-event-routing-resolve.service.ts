import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IResourceAcceptedAssociationEvent } from '../resource-accepted-association-event.model';
import { ResourceAcceptedAssociationEventService } from '../service/resource-accepted-association-event.service';

@Injectable({ providedIn: 'root' })
export class ResourceAcceptedAssociationEventRoutingResolveService implements Resolve<IResourceAcceptedAssociationEvent | null> {
  constructor(protected service: ResourceAcceptedAssociationEventService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IResourceAcceptedAssociationEvent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((resourceAcceptedAssociationEvent: HttpResponse<IResourceAcceptedAssociationEvent>) => {
          if (resourceAcceptedAssociationEvent.body) {
            return of(resourceAcceptedAssociationEvent.body);
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
