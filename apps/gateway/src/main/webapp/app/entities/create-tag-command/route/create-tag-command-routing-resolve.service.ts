import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICreateTagCommand } from '../create-tag-command.model';
import { CreateTagCommandService } from '../service/create-tag-command.service';

@Injectable({ providedIn: 'root' })
export class CreateTagCommandRoutingResolveService implements Resolve<ICreateTagCommand | null> {
  constructor(protected service: CreateTagCommandService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICreateTagCommand | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((createTagCommand: HttpResponse<ICreateTagCommand>) => {
          if (createTagCommand.body) {
            return of(createTagCommand.body);
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
