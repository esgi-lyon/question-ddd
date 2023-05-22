import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITagChoicesListedEvent } from '../tag-choices-listed-event.model';
import { TagChoicesListedEventService } from '../service/tag-choices-listed-event.service';

@Injectable({ providedIn: 'root' })
export class TagChoicesListedEventRoutingResolveService implements Resolve<ITagChoicesListedEvent | null> {
  constructor(protected service: TagChoicesListedEventService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITagChoicesListedEvent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tagChoicesListedEvent: HttpResponse<ITagChoicesListedEvent>) => {
          if (tagChoicesListedEvent.body) {
            return of(tagChoicesListedEvent.body);
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
