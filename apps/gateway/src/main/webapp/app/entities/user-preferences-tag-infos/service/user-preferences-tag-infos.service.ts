import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IUserPreferencesTagInfos, NewUserPreferencesTagInfos } from '../user-preferences-tag-infos.model';

export type PartialUpdateUserPreferencesTagInfos = Partial<IUserPreferencesTagInfos> & Pick<IUserPreferencesTagInfos, 'id'>;

export type EntityResponseType = HttpResponse<IUserPreferencesTagInfos>;
export type EntityArrayResponseType = HttpResponse<IUserPreferencesTagInfos[]>;

@Injectable({ providedIn: 'root' })
export class UserPreferencesTagInfosService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/user-preferences-tag-infos');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUserPreferencesTagInfos>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUserPreferencesTagInfos[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getUserPreferencesTagInfosIdentifier(userPreferencesTagInfos: Pick<IUserPreferencesTagInfos, 'id'>): number {
    return userPreferencesTagInfos.id;
  }

  compareUserPreferencesTagInfos(
    o1: Pick<IUserPreferencesTagInfos, 'id'> | null,
    o2: Pick<IUserPreferencesTagInfos, 'id'> | null
  ): boolean {
    return o1 && o2 ? this.getUserPreferencesTagInfosIdentifier(o1) === this.getUserPreferencesTagInfosIdentifier(o2) : o1 === o2;
  }

  addUserPreferencesTagInfosToCollectionIfMissing<Type extends Pick<IUserPreferencesTagInfos, 'id'>>(
    userPreferencesTagInfosCollection: Type[],
    ...userPreferencesTagInfosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const userPreferencesTagInfos: Type[] = userPreferencesTagInfosToCheck.filter(isPresent);
    if (userPreferencesTagInfos.length > 0) {
      const userPreferencesTagInfosCollectionIdentifiers = userPreferencesTagInfosCollection.map(
        userPreferencesTagInfosItem => this.getUserPreferencesTagInfosIdentifier(userPreferencesTagInfosItem)!
      );
      const userPreferencesTagInfosToAdd = userPreferencesTagInfos.filter(userPreferencesTagInfosItem => {
        const userPreferencesTagInfosIdentifier = this.getUserPreferencesTagInfosIdentifier(userPreferencesTagInfosItem);
        if (userPreferencesTagInfosCollectionIdentifiers.includes(userPreferencesTagInfosIdentifier)) {
          return false;
        }
        userPreferencesTagInfosCollectionIdentifiers.push(userPreferencesTagInfosIdentifier);
        return true;
      });
      return [...userPreferencesTagInfosToAdd, ...userPreferencesTagInfosCollection];
    }
    return userPreferencesTagInfosCollection;
  }
}
