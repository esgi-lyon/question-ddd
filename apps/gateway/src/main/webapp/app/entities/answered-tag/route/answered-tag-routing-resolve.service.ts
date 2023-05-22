import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAnsweredTag } from '../answered-tag.model';
import { AnsweredTagService } from '../service/answered-tag.service';

@Injectable({ providedIn: 'root' })
export class AnsweredTagRoutingResolveService implements Resolve<IAnsweredTag | null> {
  constructor(protected service: AnsweredTagService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAnsweredTag | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((answeredTag: HttpResponse<IAnsweredTag>) => {
          if (answeredTag.body) {
            return of(answeredTag.body);
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
