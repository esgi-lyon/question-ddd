import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEvaluationId } from '../evaluation-id.model';
import { EvaluationIdService } from '../service/evaluation-id.service';

@Injectable({ providedIn: 'root' })
export class EvaluationIdRoutingResolveService implements Resolve<IEvaluationId | null> {
  constructor(protected service: EvaluationIdService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEvaluationId | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((evaluationId: HttpResponse<IEvaluationId>) => {
          if (evaluationId.body) {
            return of(evaluationId.body);
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
