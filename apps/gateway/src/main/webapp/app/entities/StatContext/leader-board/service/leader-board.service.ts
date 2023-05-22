import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ILeaderBoard, NewLeaderBoard } from '../leader-board.model';

export type PartialUpdateLeaderBoard = Partial<ILeaderBoard> & Pick<ILeaderBoard, 'id'>;

export type EntityResponseType = HttpResponse<ILeaderBoard>;
export type EntityArrayResponseType = HttpResponse<ILeaderBoard[]>;

@Injectable({ providedIn: 'root' })
export class LeaderBoardService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/leader-boards', 'statcontext');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(leaderBoard: NewLeaderBoard): Observable<EntityResponseType> {
    return this.http.post<ILeaderBoard>(this.resourceUrl, leaderBoard, { observe: 'response' });
  }

  update(leaderBoard: ILeaderBoard): Observable<EntityResponseType> {
    return this.http.put<ILeaderBoard>(`${this.resourceUrl}/${this.getLeaderBoardIdentifier(leaderBoard)}`, leaderBoard, {
      observe: 'response',
    });
  }

  partialUpdate(leaderBoard: PartialUpdateLeaderBoard): Observable<EntityResponseType> {
    return this.http.patch<ILeaderBoard>(`${this.resourceUrl}/${this.getLeaderBoardIdentifier(leaderBoard)}`, leaderBoard, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILeaderBoard>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILeaderBoard[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getLeaderBoardIdentifier(leaderBoard: Pick<ILeaderBoard, 'id'>): number {
    return leaderBoard.id;
  }

  compareLeaderBoard(o1: Pick<ILeaderBoard, 'id'> | null, o2: Pick<ILeaderBoard, 'id'> | null): boolean {
    return o1 && o2 ? this.getLeaderBoardIdentifier(o1) === this.getLeaderBoardIdentifier(o2) : o1 === o2;
  }

  addLeaderBoardToCollectionIfMissing<Type extends Pick<ILeaderBoard, 'id'>>(
    leaderBoardCollection: Type[],
    ...leaderBoardsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const leaderBoards: Type[] = leaderBoardsToCheck.filter(isPresent);
    if (leaderBoards.length > 0) {
      const leaderBoardCollectionIdentifiers = leaderBoardCollection.map(
        leaderBoardItem => this.getLeaderBoardIdentifier(leaderBoardItem)!
      );
      const leaderBoardsToAdd = leaderBoards.filter(leaderBoardItem => {
        const leaderBoardIdentifier = this.getLeaderBoardIdentifier(leaderBoardItem);
        if (leaderBoardCollectionIdentifiers.includes(leaderBoardIdentifier)) {
          return false;
        }
        leaderBoardCollectionIdentifiers.push(leaderBoardIdentifier);
        return true;
      });
      return [...leaderBoardsToAdd, ...leaderBoardCollection];
    }
    return leaderBoardCollection;
  }
}
