import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IStatisticSubjectQuestion } from '../statistic-subject-question.model';
import { StatisticSubjectQuestionService } from '../service/statistic-subject-question.service';

@Injectable({ providedIn: 'root' })
export class StatisticSubjectQuestionRoutingResolveService implements Resolve<IStatisticSubjectQuestion | null> {
  constructor(protected service: StatisticSubjectQuestionService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStatisticSubjectQuestion | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((statisticSubjectQuestion: HttpResponse<IStatisticSubjectQuestion>) => {
          if (statisticSubjectQuestion.body) {
            return of(statisticSubjectQuestion.body);
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
