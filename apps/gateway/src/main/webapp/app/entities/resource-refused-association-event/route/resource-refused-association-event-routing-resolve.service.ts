import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IResourceRefusedAssociationEvent } from '../resource-refused-association-event.model';
import { ResourceRefusedAssociationEventService } from '../service/resource-refused-association-event.service';

@Injectable({ providedIn: 'root' })
export class ResourceRefusedAssociationEventRoutingResolveService implements Resolve<IResourceRefusedAssociationEvent | null> {
  constructor(protected service: ResourceRefusedAssociationEventService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IResourceRefusedAssociationEvent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((resourceRefusedAssociationEvent: HttpResponse<IResourceRefusedAssociationEvent>) => {
          if (resourceRefusedAssociationEvent.body) {
            return of(resourceRefusedAssociationEvent.body);
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
