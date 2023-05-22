import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IUserInfos, NewUserInfos } from '../user-infos.model';

export type PartialUpdateUserInfos = Partial<IUserInfos> & Pick<IUserInfos, 'id'>;

export type EntityResponseType = HttpResponse<IUserInfos>;
export type EntityArrayResponseType = HttpResponse<IUserInfos[]>;

@Injectable({ providedIn: 'root' })
export class UserInfosService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/user-infos', 'usermanagementcontext');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(userInfos: NewUserInfos): Observable<EntityResponseType> {
    return this.http.post<IUserInfos>(this.resourceUrl, userInfos, { observe: 'response' });
  }

  update(userInfos: IUserInfos): Observable<EntityResponseType> {
    return this.http.put<IUserInfos>(`${this.resourceUrl}/${this.getUserInfosIdentifier(userInfos)}`, userInfos, { observe: 'response' });
  }

  partialUpdate(userInfos: PartialUpdateUserInfos): Observable<EntityResponseType> {
    return this.http.patch<IUserInfos>(`${this.resourceUrl}/${this.getUserInfosIdentifier(userInfos)}`, userInfos, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUserInfos>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUserInfos[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getUserInfosIdentifier(userInfos: Pick<IUserInfos, 'id'>): number {
    return userInfos.id;
  }

  compareUserInfos(o1: Pick<IUserInfos, 'id'> | null, o2: Pick<IUserInfos, 'id'> | null): boolean {
    return o1 && o2 ? this.getUserInfosIdentifier(o1) === this.getUserInfosIdentifier(o2) : o1 === o2;
  }

  addUserInfosToCollectionIfMissing<Type extends Pick<IUserInfos, 'id'>>(
    userInfosCollection: Type[],
    ...userInfosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const userInfos: Type[] = userInfosToCheck.filter(isPresent);
    if (userInfos.length > 0) {
      const userInfosCollectionIdentifiers = userInfosCollection.map(userInfosItem => this.getUserInfosIdentifier(userInfosItem)!);
      const userInfosToAdd = userInfos.filter(userInfosItem => {
        const userInfosIdentifier = this.getUserInfosIdentifier(userInfosItem);
        if (userInfosCollectionIdentifiers.includes(userInfosIdentifier)) {
          return false;
        }
        userInfosCollectionIdentifiers.push(userInfosIdentifier);
        return true;
      });
      return [...userInfosToAdd, ...userInfosCollection];
    }
    return userInfosCollection;
  }
}
