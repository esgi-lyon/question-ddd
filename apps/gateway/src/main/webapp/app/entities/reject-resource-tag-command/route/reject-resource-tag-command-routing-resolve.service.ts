import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IRejectResourceTagCommand } from '../reject-resource-tag-command.model';
import { RejectResourceTagCommandService } from '../service/reject-resource-tag-command.service';

@Injectable({ providedIn: 'root' })
export class RejectResourceTagCommandRoutingResolveService implements Resolve<IRejectResourceTagCommand | null> {
  constructor(protected service: RejectResourceTagCommandService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRejectResourceTagCommand | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((rejectResourceTagCommand: HttpResponse<IRejectResourceTagCommand>) => {
          if (rejectResourceTagCommand.body) {
            return of(rejectResourceTagCommand.body);
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
