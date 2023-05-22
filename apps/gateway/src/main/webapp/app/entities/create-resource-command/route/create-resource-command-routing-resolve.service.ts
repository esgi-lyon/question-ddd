import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICreateResourceCommand } from '../create-resource-command.model';
import { CreateResourceCommandService } from '../service/create-resource-command.service';

@Injectable({ providedIn: 'root' })
export class CreateResourceCommandRoutingResolveService implements Resolve<ICreateResourceCommand | null> {
  constructor(protected service: CreateResourceCommandService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICreateResourceCommand | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((createResourceCommand: HttpResponse<ICreateResourceCommand>) => {
          if (createResourceCommand.body) {
            return of(createResourceCommand.body);
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
