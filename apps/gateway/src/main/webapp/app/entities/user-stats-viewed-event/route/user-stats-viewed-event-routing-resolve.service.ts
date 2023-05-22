import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IUserStatsViewedEvent } from '../user-stats-viewed-event.model';
import { UserStatsViewedEventService } from '../service/user-stats-viewed-event.service';

@Injectable({ providedIn: 'root' })
export class UserStatsViewedEventRoutingResolveService implements Resolve<IUserStatsViewedEvent | null> {
  constructor(protected service: UserStatsViewedEventService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserStatsViewedEvent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((userStatsViewedEvent: HttpResponse<IUserStatsViewedEvent>) => {
          if (userStatsViewedEvent.body) {
            return of(userStatsViewedEvent.body);
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
