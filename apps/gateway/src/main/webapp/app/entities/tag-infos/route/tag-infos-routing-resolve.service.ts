import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITagInfos } from '../tag-infos.model';
import { TagInfosService } from '../service/tag-infos.service';

@Injectable({ providedIn: 'root' })
export class TagInfosRoutingResolveService implements Resolve<ITagInfos | null> {
  constructor(protected service: TagInfosService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITagInfos | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tagInfos: HttpResponse<ITagInfos>) => {
          if (tagInfos.body) {
            return of(tagInfos.body);
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
