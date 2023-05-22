import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IUserWithPreferencesId, NewUserWithPreferencesId } from '../user-with-preferences-id.model';

export type PartialUpdateUserWithPreferencesId = Partial<IUserWithPreferencesId> & Pick<IUserWithPreferencesId, 'id'>;

export type EntityResponseType = HttpResponse<IUserWithPreferencesId>;
export type EntityArrayResponseType = HttpResponse<IUserWithPreferencesId[]>;

@Injectable({ providedIn: 'root' })
export class UserWithPreferencesIdService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/user-with-preferences-ids');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUserWithPreferencesId>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUserWithPreferencesId[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getUserWithPreferencesIdIdentifier(userWithPreferencesId: Pick<IUserWithPreferencesId, 'id'>): number {
    return userWithPreferencesId.id;
  }

  compareUserWithPreferencesId(o1: Pick<IUserWithPreferencesId, 'id'> | null, o2: Pick<IUserWithPreferencesId, 'id'> | null): boolean {
    return o1 && o2 ? this.getUserWithPreferencesIdIdentifier(o1) === this.getUserWithPreferencesIdIdentifier(o2) : o1 === o2;
  }

  addUserWithPreferencesIdToCollectionIfMissing<Type extends Pick<IUserWithPreferencesId, 'id'>>(
    userWithPreferencesIdCollection: Type[],
    ...userWithPreferencesIdsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const userWithPreferencesIds: Type[] = userWithPreferencesIdsToCheck.filter(isPresent);
    if (userWithPreferencesIds.length > 0) {
      const userWithPreferencesIdCollectionIdentifiers = userWithPreferencesIdCollection.map(
        userWithPreferencesIdItem => this.getUserWithPreferencesIdIdentifier(userWithPreferencesIdItem)!
      );
      const userWithPreferencesIdsToAdd = userWithPreferencesIds.filter(userWithPreferencesIdItem => {
        const userWithPreferencesIdIdentifier = this.getUserWithPreferencesIdIdentifier(userWithPreferencesIdItem);
        if (userWithPreferencesIdCollectionIdentifiers.includes(userWithPreferencesIdIdentifier)) {
          return false;
        }
        userWithPreferencesIdCollectionIdentifiers.push(userWithPreferencesIdIdentifier);
        return true;
      });
      return [...userWithPreferencesIdsToAdd, ...userWithPreferencesIdCollection];
    }
    return userWithPreferencesIdCollection;
  }
}
