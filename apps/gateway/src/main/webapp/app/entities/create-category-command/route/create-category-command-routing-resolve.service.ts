import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICreateCategoryCommand } from '../create-category-command.model';
import { CreateCategoryCommandService } from '../service/create-category-command.service';

@Injectable({ providedIn: 'root' })
export class CreateCategoryCommandRoutingResolveService implements Resolve<ICreateCategoryCommand | null> {
  constructor(protected service: CreateCategoryCommandService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICreateCategoryCommand | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((createCategoryCommand: HttpResponse<ICreateCategoryCommand>) => {
          if (createCategoryCommand.body) {
            return of(createCategoryCommand.body);
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
