import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAnsweringUser } from '../answering-user.model';
import { AnsweringUserService } from '../service/answering-user.service';

@Injectable({ providedIn: 'root' })
export class AnsweringUserRoutingResolveService implements Resolve<IAnsweringUser | null> {
  constructor(protected service: AnsweringUserService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAnsweringUser | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((answeringUser: HttpResponse<IAnsweringUser>) => {
          if (answeringUser.body) {
            return of(answeringUser.body);
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
