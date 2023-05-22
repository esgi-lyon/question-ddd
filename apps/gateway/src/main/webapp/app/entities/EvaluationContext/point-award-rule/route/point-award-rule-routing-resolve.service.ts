import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IPointAwardRule } from '../point-award-rule.model';
import { PointAwardRuleService } from '../service/point-award-rule.service';

@Injectable({ providedIn: 'root' })
export class PointAwardRuleRoutingResolveService implements Resolve<IPointAwardRule | null> {
  constructor(protected service: PointAwardRuleService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPointAwardRule | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((pointAwardRule: HttpResponse<IPointAwardRule>) => {
          if (pointAwardRule.body) {
            return of(pointAwardRule.body);
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
