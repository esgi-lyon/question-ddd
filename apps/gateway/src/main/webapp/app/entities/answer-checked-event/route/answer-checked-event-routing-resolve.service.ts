import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAnswerCheckedEvent } from '../answer-checked-event.model';
import { AnswerCheckedEventService } from '../service/answer-checked-event.service';

@Injectable({ providedIn: 'root' })
export class AnswerCheckedEventRoutingResolveService implements Resolve<IAnswerCheckedEvent | null> {
  constructor(protected service: AnswerCheckedEventService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAnswerCheckedEvent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((answerCheckedEvent: HttpResponse<IAnswerCheckedEvent>) => {
          if (answerCheckedEvent.body) {
            return of(answerCheckedEvent.body);
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
