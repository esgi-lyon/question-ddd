import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IValidateResourceTagLinkageCommand } from '../validate-resource-tag-linkage-command.model';
import { ValidateResourceTagLinkageCommandService } from '../service/validate-resource-tag-linkage-command.service';

@Injectable({ providedIn: 'root' })
export class ValidateResourceTagLinkageCommandRoutingResolveService implements Resolve<IValidateResourceTagLinkageCommand | null> {
  constructor(protected service: ValidateResourceTagLinkageCommandService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IValidateResourceTagLinkageCommand | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((validateResourceTagLinkageCommand: HttpResponse<IValidateResourceTagLinkageCommand>) => {
          if (validateResourceTagLinkageCommand.body) {
            return of(validateResourceTagLinkageCommand.body);
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
