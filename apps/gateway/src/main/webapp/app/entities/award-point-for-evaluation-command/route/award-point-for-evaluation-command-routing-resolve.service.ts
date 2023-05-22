import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAwardPointForEvaluationCommand } from '../award-point-for-evaluation-command.model';
import { AwardPointForEvaluationCommandService } from '../service/award-point-for-evaluation-command.service';

@Injectable({ providedIn: 'root' })
export class AwardPointForEvaluationCommandRoutingResolveService implements Resolve<IAwardPointForEvaluationCommand | null> {
  constructor(protected service: AwardPointForEvaluationCommandService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAwardPointForEvaluationCommand | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((awardPointForEvaluationCommand: HttpResponse<IAwardPointForEvaluationCommand>) => {
          if (awardPointForEvaluationCommand.body) {
            return of(awardPointForEvaluationCommand.body);
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
