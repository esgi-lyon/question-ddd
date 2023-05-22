import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAnsweringUser, NewAnsweringUser } from '../answering-user.model';

export type PartialUpdateAnsweringUser = Partial<IAnsweringUser> & Pick<IAnsweringUser, 'id'>;

export type EntityResponseType = HttpResponse<IAnsweringUser>;
export type EntityArrayResponseType = HttpResponse<IAnsweringUser[]>;

@Injectable({ providedIn: 'root' })
export class AnsweringUserService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/answering-users');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAnsweringUser>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAnsweringUser[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getAnsweringUserIdentifier(answeringUser: Pick<IAnsweringUser, 'id'>): number {
    return answeringUser.id;
  }

  compareAnsweringUser(o1: Pick<IAnsweringUser, 'id'> | null, o2: Pick<IAnsweringUser, 'id'> | null): boolean {
    return o1 && o2 ? this.getAnsweringUserIdentifier(o1) === this.getAnsweringUserIdentifier(o2) : o1 === o2;
  }

  addAnsweringUserToCollectionIfMissing<Type extends Pick<IAnsweringUser, 'id'>>(
    answeringUserCollection: Type[],
    ...answeringUsersToCheck: (Type | null | undefined)[]
  ): Type[] {
    const answeringUsers: Type[] = answeringUsersToCheck.filter(isPresent);
    if (answeringUsers.length > 0) {
      const answeringUserCollectionIdentifiers = answeringUserCollection.map(
        answeringUserItem => this.getAnsweringUserIdentifier(answeringUserItem)!
      );
      const answeringUsersToAdd = answeringUsers.filter(answeringUserItem => {
        const answeringUserIdentifier = this.getAnsweringUserIdentifier(answeringUserItem);
        if (answeringUserCollectionIdentifiers.includes(answeringUserIdentifier)) {
          return false;
        }
        answeringUserCollectionIdentifiers.push(answeringUserIdentifier);
        return true;
      });
      return [...answeringUsersToAdd, ...answeringUserCollection];
    }
    return answeringUserCollection;
  }
}
