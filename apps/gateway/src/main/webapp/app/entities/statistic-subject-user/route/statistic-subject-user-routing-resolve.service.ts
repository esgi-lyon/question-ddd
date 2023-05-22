import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IStatisticSubjectUser } from '../statistic-subject-user.model';
import { StatisticSubjectUserService } from '../service/statistic-subject-user.service';

@Injectable({ providedIn: 'root' })
export class StatisticSubjectUserRoutingResolveService implements Resolve<IStatisticSubjectUser | null> {
  constructor(protected service: StatisticSubjectUserService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStatisticSubjectUser | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((statisticSubjectUser: HttpResponse<IStatisticSubjectUser>) => {
          if (statisticSubjectUser.body) {
            return of(statisticSubjectUser.body);
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
