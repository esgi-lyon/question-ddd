import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ILeaderBoard } from '../leader-board.model';
import { LeaderBoardService } from '../service/leader-board.service';

@Injectable({ providedIn: 'root' })
export class LeaderBoardRoutingResolveService implements Resolve<ILeaderBoard | null> {
  constructor(protected service: LeaderBoardService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILeaderBoard | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((leaderBoard: HttpResponse<ILeaderBoard>) => {
          if (leaderBoard.body) {
            return of(leaderBoard.body);
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
