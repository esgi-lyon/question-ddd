import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITagChoicesListCommand } from '../tag-choices-list-command.model';
import { TagChoicesListCommandService } from '../service/tag-choices-list-command.service';

@Injectable({ providedIn: 'root' })
export class TagChoicesListCommandRoutingResolveService implements Resolve<ITagChoicesListCommand | null> {
  constructor(protected service: TagChoicesListCommandService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITagChoicesListCommand | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tagChoicesListCommand: HttpResponse<ITagChoicesListCommand>) => {
          if (tagChoicesListCommand.body) {
            return of(tagChoicesListCommand.body);
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
