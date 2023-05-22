import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITagCreatedEvent } from '../tag-created-event.model';
import { TagCreatedEventService } from '../service/tag-created-event.service';

@Injectable({ providedIn: 'root' })
export class TagCreatedEventRoutingResolveService implements Resolve<ITagCreatedEvent | null> {
  constructor(protected service: TagCreatedEventService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITagCreatedEvent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tagCreatedEvent: HttpResponse<ITagCreatedEvent>) => {
          if (tagCreatedEvent.body) {
            return of(tagCreatedEvent.body);
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
