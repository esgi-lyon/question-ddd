import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { INotifiedQuestionEvent } from '../notified-question-event.model';
import { NotifiedQuestionEventService } from '../service/notified-question-event.service';

@Injectable({ providedIn: 'root' })
export class NotifiedQuestionEventRoutingResolveService implements Resolve<INotifiedQuestionEvent | null> {
  constructor(protected service: NotifiedQuestionEventService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INotifiedQuestionEvent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((notifiedQuestionEvent: HttpResponse<INotifiedQuestionEvent>) => {
          if (notifiedQuestionEvent.body) {
            return of(notifiedQuestionEvent.body);
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
