import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IQuestionSentQuestionResourceTagId } from '../question-sent-question-resource-tag-id.model';
import { QuestionSentQuestionResourceTagIdService } from '../service/question-sent-question-resource-tag-id.service';

@Injectable({ providedIn: 'root' })
export class QuestionSentQuestionResourceTagIdRoutingResolveService implements Resolve<IQuestionSentQuestionResourceTagId | null> {
  constructor(protected service: QuestionSentQuestionResourceTagIdService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IQuestionSentQuestionResourceTagId | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((questionSentQuestionResourceTagId: HttpResponse<IQuestionSentQuestionResourceTagId>) => {
          if (questionSentQuestionResourceTagId.body) {
            return of(questionSentQuestionResourceTagId.body);
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
