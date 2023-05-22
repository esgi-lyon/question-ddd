import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICategoryId } from '../category-id.model';
import { CategoryIdService } from '../service/category-id.service';

@Injectable({ providedIn: 'root' })
export class CategoryIdRoutingResolveService implements Resolve<ICategoryId | null> {
  constructor(protected service: CategoryIdService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICategoryId | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((categoryId: HttpResponse<ICategoryId>) => {
          if (categoryId.body) {
            return of(categoryId.body);
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
