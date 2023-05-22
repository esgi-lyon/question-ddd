import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IQuestionStatsViewedEvent } from '../question-stats-viewed-event.model';
import { QuestionStatsViewedEventService } from '../service/question-stats-viewed-event.service';

@Injectable({ providedIn: 'root' })
export class QuestionStatsViewedEventRoutingResolveService implements Resolve<IQuestionStatsViewedEvent | null> {
  constructor(protected service: QuestionStatsViewedEventService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IQuestionStatsViewedEvent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((questionStatsViewedEvent: HttpResponse<IQuestionStatsViewedEvent>) => {
          if (questionStatsViewedEvent.body) {
            return of(questionStatsViewedEvent.body);
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
