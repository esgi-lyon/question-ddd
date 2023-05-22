import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAwardedPointEvent } from '../awarded-point-event.model';
import { AwardedPointEventService } from '../service/awarded-point-event.service';

@Injectable({ providedIn: 'root' })
export class AwardedPointEventRoutingResolveService implements Resolve<IAwardedPointEvent | null> {
  constructor(protected service: AwardedPointEventService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAwardedPointEvent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((awardedPointEvent: HttpResponse<IAwardedPointEvent>) => {
          if (awardedPointEvent.body) {
            return of(awardedPointEvent.body);
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
