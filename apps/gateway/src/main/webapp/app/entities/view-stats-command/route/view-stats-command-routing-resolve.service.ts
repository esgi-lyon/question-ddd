import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IViewStatsCommand } from '../view-stats-command.model';
import { ViewStatsCommandService } from '../service/view-stats-command.service';

@Injectable({ providedIn: 'root' })
export class ViewStatsCommandRoutingResolveService implements Resolve<IViewStatsCommand | null> {
  constructor(protected service: ViewStatsCommandService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IViewStatsCommand | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((viewStatsCommand: HttpResponse<IViewStatsCommand>) => {
          if (viewStatsCommand.body) {
            return of(viewStatsCommand.body);
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
