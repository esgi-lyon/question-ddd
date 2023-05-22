import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IStatisticSubjectUser, NewStatisticSubjectUser } from '../statistic-subject-user.model';

export type PartialUpdateStatisticSubjectUser = Partial<IStatisticSubjectUser> & Pick<IStatisticSubjectUser, 'id'>;

export type EntityResponseType = HttpResponse<IStatisticSubjectUser>;
export type EntityArrayResponseType = HttpResponse<IStatisticSubjectUser[]>;

@Injectable({ providedIn: 'root' })
export class StatisticSubjectUserService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/statistic-subject-users');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStatisticSubjectUser>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStatisticSubjectUser[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getStatisticSubjectUserIdentifier(statisticSubjectUser: Pick<IStatisticSubjectUser, 'id'>): number {
    return statisticSubjectUser.id;
  }

  compareStatisticSubjectUser(o1: Pick<IStatisticSubjectUser, 'id'> | null, o2: Pick<IStatisticSubjectUser, 'id'> | null): boolean {
    return o1 && o2 ? this.getStatisticSubjectUserIdentifier(o1) === this.getStatisticSubjectUserIdentifier(o2) : o1 === o2;
  }

  addStatisticSubjectUserToCollectionIfMissing<Type extends Pick<IStatisticSubjectUser, 'id'>>(
    statisticSubjectUserCollection: Type[],
    ...statisticSubjectUsersToCheck: (Type | null | undefined)[]
  ): Type[] {
    const statisticSubjectUsers: Type[] = statisticSubjectUsersToCheck.filter(isPresent);
    if (statisticSubjectUsers.length > 0) {
      const statisticSubjectUserCollectionIdentifiers = statisticSubjectUserCollection.map(
        statisticSubjectUserItem => this.getStatisticSubjectUserIdentifier(statisticSubjectUserItem)!
      );
      const statisticSubjectUsersToAdd = statisticSubjectUsers.filter(statisticSubjectUserItem => {
        const statisticSubjectUserIdentifier = this.getStatisticSubjectUserIdentifier(statisticSubjectUserItem);
        if (statisticSubjectUserCollectionIdentifiers.includes(statisticSubjectUserIdentifier)) {
          return false;
        }
        statisticSubjectUserCollectionIdentifiers.push(statisticSubjectUserIdentifier);
        return true;
      });
      return [...statisticSubjectUsersToAdd, ...statisticSubjectUserCollection];
    }
    return statisticSubjectUserCollection;
  }
}
