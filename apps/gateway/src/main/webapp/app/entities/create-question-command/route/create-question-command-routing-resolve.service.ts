import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICreateQuestionCommand } from '../create-question-command.model';
import { CreateQuestionCommandService } from '../service/create-question-command.service';

@Injectable({ providedIn: 'root' })
export class CreateQuestionCommandRoutingResolveService implements Resolve<ICreateQuestionCommand | null> {
  constructor(protected service: CreateQuestionCommandService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICreateQuestionCommand | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((createQuestionCommand: HttpResponse<ICreateQuestionCommand>) => {
          if (createQuestionCommand.body) {
            return of(createQuestionCommand.body);
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
