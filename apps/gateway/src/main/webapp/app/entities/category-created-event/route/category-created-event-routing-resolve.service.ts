import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICategoryCreatedEvent } from '../category-created-event.model';
import { CategoryCreatedEventService } from '../service/category-created-event.service';

@Injectable({ providedIn: 'root' })
export class CategoryCreatedEventRoutingResolveService implements Resolve<ICategoryCreatedEvent | null> {
  constructor(protected service: CategoryCreatedEventService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICategoryCreatedEvent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((categoryCreatedEvent: HttpResponse<ICategoryCreatedEvent>) => {
          if (categoryCreatedEvent.body) {
            return of(categoryCreatedEvent.body);
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
