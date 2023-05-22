import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISendQuestionByTagsPreferencesCommand } from '../send-question-by-tags-preferences-command.model';
import { SendQuestionByTagsPreferencesCommandService } from '../service/send-question-by-tags-preferences-command.service';

@Injectable({ providedIn: 'root' })
export class SendQuestionByTagsPreferencesCommandRoutingResolveService implements Resolve<ISendQuestionByTagsPreferencesCommand | null> {
  constructor(protected service: SendQuestionByTagsPreferencesCommandService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISendQuestionByTagsPreferencesCommand | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sendQuestionByTagsPreferencesCommand: HttpResponse<ISendQuestionByTagsPreferencesCommand>) => {
          if (sendQuestionByTagsPreferencesCommand.body) {
            return of(sendQuestionByTagsPreferencesCommand.body);
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
