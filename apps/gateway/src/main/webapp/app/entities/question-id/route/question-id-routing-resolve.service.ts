import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IQuestionId } from '../question-id.model';
import { QuestionIdService } from '../service/question-id.service';

@Injectable({ providedIn: 'root' })
export class QuestionIdRoutingResolveService implements Resolve<IQuestionId | null> {
  constructor(protected service: QuestionIdService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IQuestionId | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((questionId: HttpResponse<IQuestionId>) => {
          if (questionId.body) {
            return of(questionId.body);
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
