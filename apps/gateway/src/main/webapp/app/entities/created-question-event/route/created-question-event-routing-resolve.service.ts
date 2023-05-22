import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICreatedQuestionEvent } from '../created-question-event.model';
import { CreatedQuestionEventService } from '../service/created-question-event.service';

@Injectable({ providedIn: 'root' })
export class CreatedQuestionEventRoutingResolveService implements Resolve<ICreatedQuestionEvent | null> {
  constructor(protected service: CreatedQuestionEventService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICreatedQuestionEvent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((createdQuestionEvent: HttpResponse<ICreatedQuestionEvent>) => {
          if (createdQuestionEvent.body) {
            return of(createdQuestionEvent.body);
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
