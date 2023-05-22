import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAnswerSubmitCommand } from '../answer-submit-command.model';
import { AnswerSubmitCommandService } from '../service/answer-submit-command.service';

@Injectable({ providedIn: 'root' })
export class AnswerSubmitCommandRoutingResolveService implements Resolve<IAnswerSubmitCommand | null> {
  constructor(protected service: AnswerSubmitCommandService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAnswerSubmitCommand | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((answerSubmitCommand: HttpResponse<IAnswerSubmitCommand>) => {
          if (answerSubmitCommand.body) {
            return of(answerSubmitCommand.body);
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
