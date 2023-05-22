import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEvaluationCreatedEvent } from '../evaluation-created-event.model';
import { EvaluationCreatedEventService } from '../service/evaluation-created-event.service';

@Injectable({ providedIn: 'root' })
export class EvaluationCreatedEventRoutingResolveService implements Resolve<IEvaluationCreatedEvent | null> {
  constructor(protected service: EvaluationCreatedEventService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEvaluationCreatedEvent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((evaluationCreatedEvent: HttpResponse<IEvaluationCreatedEvent>) => {
          if (evaluationCreatedEvent.body) {
            return of(evaluationCreatedEvent.body);
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
