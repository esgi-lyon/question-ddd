import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IStatisticSubjectTag } from '../statistic-subject-tag.model';
import { StatisticSubjectTagService } from '../service/statistic-subject-tag.service';

@Injectable({ providedIn: 'root' })
export class StatisticSubjectTagRoutingResolveService implements Resolve<IStatisticSubjectTag | null> {
  constructor(protected service: StatisticSubjectTagService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStatisticSubjectTag | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((statisticSubjectTag: HttpResponse<IStatisticSubjectTag>) => {
          if (statisticSubjectTag.body) {
            return of(statisticSubjectTag.body);
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
