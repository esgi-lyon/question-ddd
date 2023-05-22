import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICreateEvaluationCommand } from '../create-evaluation-command.model';
import { CreateEvaluationCommandService } from '../service/create-evaluation-command.service';

@Injectable({ providedIn: 'root' })
export class CreateEvaluationCommandRoutingResolveService implements Resolve<ICreateEvaluationCommand | null> {
  constructor(protected service: CreateEvaluationCommandService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICreateEvaluationCommand | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((createEvaluationCommand: HttpResponse<ICreateEvaluationCommand>) => {
          if (createEvaluationCommand.body) {
            return of(createEvaluationCommand.body);
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
