import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEvaluationQuestion } from '../evaluation-question.model';
import { EvaluationQuestionService } from '../service/evaluation-question.service';

@Injectable({ providedIn: 'root' })
export class EvaluationQuestionRoutingResolveService implements Resolve<IEvaluationQuestion | null> {
  constructor(protected service: EvaluationQuestionService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEvaluationQuestion | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((evaluationQuestion: HttpResponse<IEvaluationQuestion>) => {
          if (evaluationQuestion.body) {
            return of(evaluationQuestion.body);
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
