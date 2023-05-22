import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IQuestionSent } from '../question-sent.model';
import { QuestionSentService } from '../service/question-sent.service';

@Injectable({ providedIn: 'root' })
export class QuestionSentRoutingResolveService implements Resolve<IQuestionSent | null> {
  constructor(protected service: QuestionSentService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IQuestionSent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((questionSent: HttpResponse<IQuestionSent>) => {
          if (questionSent.body) {
            return of(questionSent.body);
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
