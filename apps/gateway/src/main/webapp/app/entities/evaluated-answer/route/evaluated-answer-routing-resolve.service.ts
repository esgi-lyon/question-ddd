import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEvaluatedAnswer } from '../evaluated-answer.model';
import { EvaluatedAnswerService } from '../service/evaluated-answer.service';

@Injectable({ providedIn: 'root' })
export class EvaluatedAnswerRoutingResolveService implements Resolve<IEvaluatedAnswer | null> {
  constructor(protected service: EvaluatedAnswerService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEvaluatedAnswer | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((evaluatedAnswer: HttpResponse<IEvaluatedAnswer>) => {
          if (evaluatedAnswer.body) {
            return of(evaluatedAnswer.body);
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
