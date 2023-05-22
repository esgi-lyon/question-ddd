import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITagStatsViewedEvent } from '../tag-stats-viewed-event.model';
import { TagStatsViewedEventService } from '../service/tag-stats-viewed-event.service';

@Injectable({ providedIn: 'root' })
export class TagStatsViewedEventRoutingResolveService implements Resolve<ITagStatsViewedEvent | null> {
  constructor(protected service: TagStatsViewedEventService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITagStatsViewedEvent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tagStatsViewedEvent: HttpResponse<ITagStatsViewedEvent>) => {
          if (tagStatsViewedEvent.body) {
            return of(tagStatsViewedEvent.body);
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
