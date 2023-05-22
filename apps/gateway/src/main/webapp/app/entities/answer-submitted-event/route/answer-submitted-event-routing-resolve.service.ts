import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAnswerSubmittedEvent } from '../answer-submitted-event.model';
import { AnswerSubmittedEventService } from '../service/answer-submitted-event.service';

@Injectable({ providedIn: 'root' })
export class AnswerSubmittedEventRoutingResolveService implements Resolve<IAnswerSubmittedEvent | null> {
  constructor(protected service: AnswerSubmittedEventService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAnswerSubmittedEvent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((answerSubmittedEvent: HttpResponse<IAnswerSubmittedEvent>) => {
          if (answerSubmittedEvent.body) {
            return of(answerSubmittedEvent.body);
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
