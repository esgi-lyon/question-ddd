import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICheckAnswerCommand } from '../check-answer-command.model';
import { CheckAnswerCommandService } from '../service/check-answer-command.service';

@Injectable({ providedIn: 'root' })
export class CheckAnswerCommandRoutingResolveService implements Resolve<ICheckAnswerCommand | null> {
  constructor(protected service: CheckAnswerCommandService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICheckAnswerCommand | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((checkAnswerCommand: HttpResponse<ICheckAnswerCommand>) => {
          if (checkAnswerCommand.body) {
            return of(checkAnswerCommand.body);
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
