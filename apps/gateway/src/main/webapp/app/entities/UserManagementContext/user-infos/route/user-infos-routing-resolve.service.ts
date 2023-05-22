import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IUserInfos } from '../user-infos.model';
import { UserInfosService } from '../service/user-infos.service';

@Injectable({ providedIn: 'root' })
export class UserInfosRoutingResolveService implements Resolve<IUserInfos | null> {
  constructor(protected service: UserInfosService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserInfos | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((userInfos: HttpResponse<IUserInfos>) => {
          if (userInfos.body) {
            return of(userInfos.body);
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
