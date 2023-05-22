import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IQuestionResourceTagInfos } from '../question-resource-tag-infos.model';
import { QuestionResourceTagInfosService } from '../service/question-resource-tag-infos.service';

@Injectable({ providedIn: 'root' })
export class QuestionResourceTagInfosRoutingResolveService implements Resolve<IQuestionResourceTagInfos | null> {
  constructor(protected service: QuestionResourceTagInfosService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IQuestionResourceTagInfos | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((questionResourceTagInfos: HttpResponse<IQuestionResourceTagInfos>) => {
          if (questionResourceTagInfos.body) {
            return of(questionResourceTagInfos.body);
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
