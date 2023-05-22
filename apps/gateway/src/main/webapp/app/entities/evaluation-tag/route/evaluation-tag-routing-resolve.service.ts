import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEvaluationTag } from '../evaluation-tag.model';
import { EvaluationTagService } from '../service/evaluation-tag.service';

@Injectable({ providedIn: 'root' })
export class EvaluationTagRoutingResolveService implements Resolve<IEvaluationTag | null> {
  constructor(protected service: EvaluationTagService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEvaluationTag | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((evaluationTag: HttpResponse<IEvaluationTag>) => {
          if (evaluationTag.body) {
            return of(evaluationTag.body);
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
