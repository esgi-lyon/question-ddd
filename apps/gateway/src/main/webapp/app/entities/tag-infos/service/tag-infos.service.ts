import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITagInfos, NewTagInfos } from '../tag-infos.model';

export type PartialUpdateTagInfos = Partial<ITagInfos> & Pick<ITagInfos, 'id'>;

export type EntityResponseType = HttpResponse<ITagInfos>;
export type EntityArrayResponseType = HttpResponse<ITagInfos[]>;

@Injectable({ providedIn: 'root' })
export class TagInfosService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/tag-infos');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITagInfos>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITagInfos[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getTagInfosIdentifier(tagInfos: Pick<ITagInfos, 'id'>): number {
    return tagInfos.id;
  }

  compareTagInfos(o1: Pick<ITagInfos, 'id'> | null, o2: Pick<ITagInfos, 'id'> | null): boolean {
    return o1 && o2 ? this.getTagInfosIdentifier(o1) === this.getTagInfosIdentifier(o2) : o1 === o2;
  }

  addTagInfosToCollectionIfMissing<Type extends Pick<ITagInfos, 'id'>>(
    tagInfosCollection: Type[],
    ...tagInfosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const tagInfos: Type[] = tagInfosToCheck.filter(isPresent);
    if (tagInfos.length > 0) {
      const tagInfosCollectionIdentifiers = tagInfosCollection.map(tagInfosItem => this.getTagInfosIdentifier(tagInfosItem)!);
      const tagInfosToAdd = tagInfos.filter(tagInfosItem => {
        const tagInfosIdentifier = this.getTagInfosIdentifier(tagInfosItem);
        if (tagInfosCollectionIdentifiers.includes(tagInfosIdentifier)) {
          return false;
        }
        tagInfosCollectionIdentifiers.push(tagInfosIdentifier);
        return true;
      });
      return [...tagInfosToAdd, ...tagInfosCollection];
    }
    return tagInfosCollection;
  }
}
