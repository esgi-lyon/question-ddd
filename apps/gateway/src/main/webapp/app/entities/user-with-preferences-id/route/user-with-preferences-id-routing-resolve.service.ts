import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IUserWithPreferencesId } from '../user-with-preferences-id.model';
import { UserWithPreferencesIdService } from '../service/user-with-preferences-id.service';

@Injectable({ providedIn: 'root' })
export class UserWithPreferencesIdRoutingResolveService implements Resolve<IUserWithPreferencesId | null> {
  constructor(protected service: UserWithPreferencesIdService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserWithPreferencesId | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((userWithPreferencesId: HttpResponse<IUserWithPreferencesId>) => {
          if (userWithPreferencesId.body) {
            return of(userWithPreferencesId.body);
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
