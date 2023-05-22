import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IQuestionResource } from '../question-resource.model';
import { QuestionResourceService } from '../service/question-resource.service';

@Injectable({ providedIn: 'root' })
export class QuestionResourceRoutingResolveService implements Resolve<IQuestionResource | null> {
  constructor(protected service: QuestionResourceService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IQuestionResource | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((questionResource: HttpResponse<IQuestionResource>) => {
          if (questionResource.body) {
            return of(questionResource.body);
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
