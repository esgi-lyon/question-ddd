import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IUserPreferencesTagInfos } from '../user-preferences-tag-infos.model';
import { UserPreferencesTagInfosService } from '../service/user-preferences-tag-infos.service';

@Injectable({ providedIn: 'root' })
export class UserPreferencesTagInfosRoutingResolveService implements Resolve<IUserPreferencesTagInfos | null> {
  constructor(protected service: UserPreferencesTagInfosService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserPreferencesTagInfos | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((userPreferencesTagInfos: HttpResponse<IUserPreferencesTagInfos>) => {
          if (userPreferencesTagInfos.body) {
            return of(userPreferencesTagInfos.body);
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
