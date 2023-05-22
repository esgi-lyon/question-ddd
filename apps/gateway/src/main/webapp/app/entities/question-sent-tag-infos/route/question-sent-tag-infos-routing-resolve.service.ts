import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IQuestionSentTagInfos } from '../question-sent-tag-infos.model';
import { QuestionSentTagInfosService } from '../service/question-sent-tag-infos.service';

@Injectable({ providedIn: 'root' })
export class QuestionSentTagInfosRoutingResolveService implements Resolve<IQuestionSentTagInfos | null> {
  constructor(protected service: QuestionSentTagInfosService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IQuestionSentTagInfos | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((questionSentTagInfos: HttpResponse<IQuestionSentTagInfos>) => {
          if (questionSentTagInfos.body) {
            return of(questionSentTagInfos.body);
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
